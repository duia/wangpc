package com.wpc.shiro;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.wpc.common.HttpConstant;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.PasswordService;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import com.wpc.admin.dao.AuthPermissionDao;
import com.wpc.admin.dao.AuthRoleDao;
import com.wpc.admin.dao.UserDao;
import com.wpc.admin.entity.AuthPermission;
import com.wpc.admin.entity.AuthRole;
import com.wpc.admin.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

public class ShiroRealm extends AuthorizingRealm {

    private static final String OR_OPERATOR = " or ";
    private static final String AND_OPERATOR = " and ";
    private static final String NOT_OPERATOR = "not ";

    @Autowired
    private UserDao userDao;
    @Autowired
    private AuthRoleDao authRoleDao;
    @Autowired
    private AuthPermissionDao authPermissionDao;

    private PasswordService passwordService;

    public void setPasswordService(PasswordService passwordService) {
        this.passwordService = passwordService;
    }

    /*
         * 授权
         */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 根据用户配置用户与权限  
        if (principals == null) {
            throw new AuthorizationException(
                    "PrincipalCollection method argument cannot be null.");
        }
        String username = (String) getAvailablePrincipal(principals);
        List<String> roles = new ArrayList<String>();
        List<String> permissions = new ArrayList<String>();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        if ("admin".equals(username)) {
            for (AuthRole authRole : authRoleDao.queryAll()) {
                roles.add(authRole.getRoleCode());
            }
            for (AuthPermission authPermission : authPermissionDao.queryAll()) {
                permissions.add(authPermission.getPermissionCode());
            }
        } else {
            // 从数据库中获取用户
            User user = userDao.getUserByAccount(username);
            // 根据用户名查询出用户 判断用户信息的有效性 然获取用户的角色权限 授权 
            for (AuthRole authRole : authRoleDao.queryRoleByUserId(user.getId())) {
                roles.add(authRole.getRoleCode());
                for (AuthPermission authPermission : authPermissionDao.queryPermissionByRoleId(authRole.getId())) {
                    permissions.add(authPermission.getPermissionCode());
                }
            }
        }
        info.addRoles(roles);
        info.addStringPermissions(permissions);
        return info;
    }

    /* 
     * 认证回调函数,登录时调用. 获取认证信息
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        User user = userDao.getUserByAccount(token.getUsername());
        if (user == null) {
            throw new UnknownAccountException();//没找到帐号
        }
//        if(Boolean.TRUE.equals(user.getLocked())) {
//            throw new LockedAccountException(); //帐号锁定
//        }
        this.setSession(HttpConstant.LOGIN_USER, user);
        return new SimpleAuthenticationInfo(
                user.getUsername(),
//                passwordService.encryptPassword(user.getPassword()),
                user.getPassword(),
                getName());
    }

    /**
     * 将一些数据放到ShiroSession中,以便于其它地方使用
     *
     */
    private void setSession(Object key, Object value) {
        Subject currentUser = SecurityUtils.getSubject();
        if (null != currentUser) {
            Session session = currentUser.getSession();
//            System.out.println("Session默认超时时间为[" + session.getTimeout() + "]毫秒");
            if (null != session) {
                session.setAttribute(key, value);
            }
        }
    }

    /**
     * 支持or and not 关键词  不支持and or混用
     * @param principals
     * @param permission
     * @return
     */
    public boolean isPermitted(PrincipalCollection principals, String permission) {
        if(permission.contains(OR_OPERATOR)) {
            String[] permissions = permission.split(OR_OPERATOR);
            for(String orPermission : permissions) {
                if(isPermittedWithNotOperator(principals, orPermission)) {
                    return true;
                }
            }
            return false;
        } else if(permission.contains(AND_OPERATOR)) {
            String[] permissions = permission.split(AND_OPERATOR);
            for(String orPermission : permissions) {
                if(!isPermittedWithNotOperator(principals, orPermission)) {
                    return false;
                }
            }
            return true;
        } else {
            return isPermittedWithNotOperator(principals, permission);
        }
    }

    private boolean isPermittedWithNotOperator(PrincipalCollection principals, String permission) {
        if(permission.startsWith(NOT_OPERATOR)) {
            return !super.isPermitted(principals, permission.substring(NOT_OPERATOR.length()));
        } else {
            return super.isPermitted(principals, permission);
        }
    }

}
