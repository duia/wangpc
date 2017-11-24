package com.wpc.common;

import com.wpc.common.cache.WpcCache;
import com.wpc.common.security.shiro.ShiroRealm;
import com.wpc.sys.dao.UserDao;
import com.wpc.sys.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class SessionUtil {

    private static UserDao userDao = SpringContextHolder.getBean(UserDao.class);
    private static WpcCache wpcCache = SpringContextHolder.getBean(WpcCache.class);

    public static final String USER_CACHE = "userCache";
    public static final String USER_CACHE_ID_ = "id_";
    public static final String USER_CACHE_LOGIN_NAME_ = "ln_";

    public static final String CACHE_AUTH_INFO = "authInfo";

    public static Session getSession(){
        try{
            Subject subject = SecurityUtils.getSubject();
            Session session = subject.getSession(false);
            if (session == null){
                session = subject.getSession();
            }
            if (session != null){
                return session;
            }
        }catch (InvalidSessionException e){

        }
        return null;
    }

    /**
     * 获取授权主要对象
     */
    public static Subject getSubject(){
        return SecurityUtils.getSubject();
    }

    /**
     * 获取当前登录者对象
     */
    public static ShiroRealm.Principal getPrincipal(){
        try{
            Subject subject = SecurityUtils.getSubject();
            ShiroRealm.Principal principal = (ShiroRealm.Principal)subject.getPrincipal();
            if (principal != null){
                return principal;
            }
//			subject.logout();
        }catch (UnavailableSecurityManagerException | InvalidSessionException e) {

        }
        return null;
    }

    public static Object getAuthInfo(String key) {
        return getAuthInfo(key, null);
    }

    public static Object getAuthInfo(String key, Object defaultValue) {
        Object obj = getSession().getAttribute(key);
        return obj==null?defaultValue:obj;
    }

    public static void putAuthInfo(String key, Object value) {
        getSession().setAttribute(key, value);
    }

    public static void removeAuthInfo(String key) {
        getSession().removeAttribute(key);
    }

    /**
     * 获取当前用户
     * @return 取不到返回 new User()
     */
    public static User getUser(){
        ShiroRealm.Principal principal = getPrincipal();
        if (principal!=null){
            User user = get(principal.getId());
            if (user != null){
                return user;
            }
            return new User();
        }
        // 如果没有登录，则返回实例化空的User对象。
        return new User();
    }

    /**
     * 根据ID获取用户
     * @param id
     * @return 取不到返回null
     */
    public static User get(Long id){
        User user = (User)wpcCache.hGet(USER_CACHE, USER_CACHE_ID_ + id);
        if (user ==  null){
            user = userDao.findById(id);
            if (user == null){
                return null;
            }
            wpcCache.hSet(USER_CACHE, USER_CACHE_ID_ + user.getId(), user);
            wpcCache.hSet(USER_CACHE, USER_CACHE_LOGIN_NAME_ + user.getLoginName(), user);
        }
        return user;
    }

    /**
     * 根据登录名获取用户
     * @param loginName
     * @return 取不到返回null
     */
    public static User getByLoginName(String loginName){
        User user = (User)wpcCache.hGet(USER_CACHE, USER_CACHE_LOGIN_NAME_ + loginName);
        if (user == null){
            user = userDao.getUserByLoginName(loginName);
            if (user == null){
                return null;
            }
            wpcCache.hSet(USER_CACHE, USER_CACHE_ID_ + user.getId(), user);
            wpcCache.hSet(USER_CACHE, USER_CACHE_LOGIN_NAME_ + user.getLoginName(), user);
        }
        return user;
    }

    /**
     * 清除指定用户缓存
     * @param user
     */
    public static void clearCache(User user){
        wpcCache.delete(USER_CACHE, USER_CACHE_ID_ + user.getId());
        wpcCache.delete(USER_CACHE, USER_CACHE_LOGIN_NAME_ + user.getLoginName());
    }

}
