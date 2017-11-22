package com.wpc.shiro;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.wpc.common.utils.net.IpUtils;
import com.wpc.sys.dao.PermissionDao;
import com.wpc.sys.dao.RoleDao;
import com.wpc.sys.dao.UserDao;
import com.wpc.sys.model.Permission;
import com.wpc.sys.model.Role;
import com.wpc.sys.model.User;
import com.wpc.util.SessionUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.PasswordService;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import org.springframework.beans.factory.annotation.Autowired;

public class ShiroRealm extends AuthorizingRealm {

    private static final String OR_OPERATOR = " or ";
    private static final String AND_OPERATOR = " and ";
    private static final String NOT_OPERATOR = "not ";

    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private PermissionDao permissionDao;

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
        // 从数据库中获取用户
        User user = userDao.getUserByLoginName(username);
        if (null == user) {
            return null;
        }
        if ("admin".equals(username)) {//可以修改为别的验证是否是超级管理员
            for (Role role : roleDao.queryAll()) {
                roles.add(role.getRoleCode());
            }
            for (Permission permission : permissionDao.queryAll()) {
                permissions.add(permission.getPermissionCode());
            }
        } else {
            // 根据用户名查询出用户 判断用户信息的有效性 然获取用户的角色权限 授权
            for (Role role : roleDao.queryRoleByUserId(user.getId())) {
                roles.add(role.getRoleCode());
                for (Permission permission : permissionDao.queryPermissionByRoleId(role.getId())) {
                    permissions.add(permission.getPermissionCode());
                }
            }
        }
        info.addRoles(roles);
        info.addStringPermissions(permissions);

        // 更新登录IP和时间
        User entity = new User();
        entity.setId(user.getId());
        entity.setLoginDate(new Date());
        entity.setLoginIp(IpUtils.getIpAddress(SessionUtil.getRequest()));
        userDao.update(entity);
        // 记录登录日志
//        LogUtils.saveLog(Servlets.getRequest(), "系统登录");

        return info;
    }

    /**
     * 获取权限授权信息，如果缓存中存在，则直接从缓存中获取，否则就重新获取， 登录成功后调用
     */
    @Override
    protected AuthorizationInfo getAuthorizationInfo(PrincipalCollection principals) {
        if (principals == null) {
            return null;
        }

        AuthorizationInfo info = null;

        info = (AuthorizationInfo)SessionUtil.getAuthInfo(SessionUtil.CACHE_AUTH_INFO);

        if (info == null) {
            info = doGetAuthorizationInfo(principals);
            if (info != null) {
                SessionUtil.putAuthInfo(SessionUtil.CACHE_AUTH_INFO, info);
            }
        }

        return info;
    }

    /* 
     * 认证回调函数,登录时调用. 获取认证信息
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        User user = userDao.getUserByLoginName(token.getUsername());
        if (user == null) {
            throw new UnknownAccountException();//没找到帐号
        }
//        if(Boolean.TRUE.equals(user.getLoginFlag())) {
//            throw new LockedAccountException(); //帐号锁定
//        }
//        this.setSession(HttpConstant.LOGIN_USER, user);
        return new SimpleAuthenticationInfo(
                user.getLoginName(),
//                passwordService.encryptPassword(user.getPassword()),
                user.getPassword(),
                getName());
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
