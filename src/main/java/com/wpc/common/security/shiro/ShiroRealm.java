package com.wpc.common.security.shiro;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.wpc.common.Global;
import com.wpc.common.security.shiro.session.SessionDAO;
import com.wpc.common.utils.Servlets;
import com.wpc.common.utils.net.IpUtils;
import com.wpc.sys.dao.PermissionDao;
import com.wpc.sys.dao.RoleDao;
import com.wpc.sys.dao.UserDao;
import com.wpc.sys.model.Permission;
import com.wpc.sys.model.Role;
import com.wpc.sys.model.User;
import com.wpc.common.SessionUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.PasswordService;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class ShiroRealm extends AuthorizingRealm {

    private static final Logger logger = LoggerFactory.getLogger(ShiroRealm.class);

    private static final String OR_OPERATOR = " or ";
    private static final String AND_OPERATOR = " and ";
    private static final String NOT_OPERATOR = "not ";

    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private PermissionDao permissionDao;

    @Autowired
    private SessionDAO sessionDAO;

    private PasswordService passwordService;

    public void setPasswordService(PasswordService passwordService) {
        this.passwordService = passwordService;
    }

    /*
     * 认证回调函数,登录时调用. 获取认证信息
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;

        int activeSessionSize = sessionDAO.getActiveSessions(false).size();
        if (logger.isDebugEnabled()){
            logger.debug("login submit, active session size: {}, username: {}", activeSessionSize, token.getUsername());
        }

        User user = userDao.getUserByLoginName(token.getUsername());
        if (user == null) {
            throw new UnknownAccountException();//没找到帐号
        }
//        if(Boolean.TRUE.equals(user.getLoginFlag())) {
//            throw new LockedAccountException(); //帐号锁定
//        }
        return new SimpleAuthenticationInfo(
                new Principal(user),
//                passwordService.encryptPassword(user.getPassword()),
                user.getPassword(),
                getName());
    }

    /*
         * 授权
         */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 根据用户配置用户与权限  
        Principal principal = (Principal)  getAvailablePrincipal(principals);
        // 获取当前已登录的用户
        if (!Global.TRUE.equals(Global.getConfig("user.multiAccountLogin"))){
            Collection<Session> sessions = sessionDAO.getActiveSessions(true, principal, SessionUtil.getSession());
            if (sessions.size() > 0){
                // 如果是登录进来的，则踢出已在线用户
                if (SessionUtil.getSubject().isAuthenticated()){//SessionUtil.getSubject().isRemembered() 记住我进来的
                    for (Session session : sessions){
                        sessionDAO.delete(session);
                    }
                }
                // 记住我进来的，并且当前用户已登录，则退出当前用户提示信息。
                else{
                    SessionUtil.getSubject().logout();
                    throw new AuthenticationException("msg: 账号已在其它地方登录，请重新登录。");
                }
            }
        }
        List<String> roles = new ArrayList<String>();
        List<String> permissions = new ArrayList<String>();
        // 添加用户权限
        permissions.add("user");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 从数据库中获取用户
        User user = userDao.getUserByLoginName(principal.getLoginName());
        if (null == user) {
            return null;
        }
        if (new Long(1).equals(user.getId())) {//可以修改为别的验证是否是超级管理员
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
        entity.setLoginIp(IpUtils.getIpAddress(Servlets.getRequest()));
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

    /**
     * 授权用户信息
     */
    public static class Principal implements Serializable {

        private static final long serialVersionUID = 1L;

        private Long id; // 编号
        private String loginName; // 登录名
        private String name; // 姓名

        public Principal(User user) {
            this.id = user.getId();
            this.loginName = user.getLoginName();
            this.name = user.getUsername();
        }

        public Long getId() {
            return id;
        }

        public String getLoginName() {
            return loginName;
        }

        public String getName() {
            return name;
        }

        /**
         * 获取SESSIONID
         */
        public String getSessionid() {
            try{
                return (String) SessionUtil.getSession().getId();
            }catch (Exception e) {
                return "";
            }
        }

        @Override
        public String toString() {
            return id.toString();
        }

    }

}
