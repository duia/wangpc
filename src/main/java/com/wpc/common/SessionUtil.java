package com.wpc.common;

import com.wpc.common.cache.AbstractCache;
import com.wpc.common.cache.WpcCache;
import com.wpc.common.security.shiro.ShiroRealm.Principal;
import com.wpc.sys.dao.UserDao;
import com.wpc.sys.model.Menu;
import com.wpc.sys.model.User;
import com.wpc.sys.service.MenuService;
import com.wpc.sys.service.impl.MenuServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import java.util.List;

public class SessionUtil {

    private static MenuService menuService = SpringContextHolder.getBean(MenuServiceImpl.class);

    private static UserDao userDao = SpringContextHolder.getBean(UserDao.class);
    private static AbstractCache ache = SpringContextHolder.getBean(WpcCache.class);

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
    public static Principal getPrincipal(){
        try{
            Subject subject = SecurityUtils.getSubject();
            Principal principal = (Principal)subject.getPrincipal();
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
        Object obj = ache.get(key);
        return obj==null?defaultValue:obj;
    }

    public static void putAuthInfo(String key, Object value) {
        ache.set(key, value);
    }

    public static void removeAuthInfo(String key) {
        ache.delete(key);
    }

    /**
     * 获取当前用户
     * @return 取不到返回 new User()
     */
    public static User getUser(){
        Principal principal = getPrincipal();
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
        User user = (User)ache.hGet(USER_CACHE, USER_CACHE_ID_ + id);
        if (user ==  null){
            user = userDao.findById(id);
            if (user == null){
                return null;
            }
            ache.hSet(USER_CACHE, USER_CACHE_ID_ + user.getId(), user);
            ache.hSet(USER_CACHE, USER_CACHE_LOGIN_NAME_ + user.getLoginName(), user);
        }
        return user;
    }

    /**
     * 根据登录名获取用户
     * @param loginName
     * @return 取不到返回null
     */
    public static User getByLoginName(String loginName){
        User user = (User)ache.hGet(USER_CACHE, USER_CACHE_LOGIN_NAME_ + loginName);
        if (user == null){
            user = userDao.getUserByLoginName(loginName);
            if (user == null){
                return null;
            }
            ache.hSet(USER_CACHE, USER_CACHE_ID_ + user.getId(), user);
            ache.hSet(USER_CACHE, USER_CACHE_LOGIN_NAME_ + user.getLoginName(), user);
        }
        return user;
    }

    /**
     * 清除指定用户缓存
     * @param user
     */
    public static void clearCache(User user){
        ache.delete(USER_CACHE, USER_CACHE_ID_ + user.getId());
        ache.delete(USER_CACHE, USER_CACHE_LOGIN_NAME_ + user.getLoginName());
    }

    /**
     * 获取当前用户授权菜单
     * @return
     */
    public static List<Menu> getUserMenuList(){
        return menuService.getLeftMenu();
    }

}
