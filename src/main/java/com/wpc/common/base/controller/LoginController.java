package com.wpc.common.base.controller;

import javax.servlet.http.HttpServletRequest;

import com.wpc.common.SessionUtil;
import com.wpc.common.security.shiro.MyFormAuthenticationFilter;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
@RequestMapping(value = "/")
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private MyFormAuthenticationFilter formAuthenticationFilter;
    @Autowired
    private SessionDAO sessionDAO;

	@RequestMapping(value="/login", method = RequestMethod.GET)
    public String login() {
        return "login";  
    }
    
    @RequestMapping(value = "/logout")
    public String doLogout() {
        logger.info("======用户"+ SessionUtil.getUser().getLoginName()+"退出了系统");
        SecurityUtils.getSubject().logout();
        return "login";
    }
  
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String doLogin(HttpServletRequest request, Model model) {
        logger.info("======用户进入了ShiroController的/doLogin.html");

        Subject subject = SecurityUtils.getSubject();
        Principal principal = (Principal)subject.getPrincipal();
        if (principal != null) {
            SavedRequest savedRequest = WebUtils.getSavedRequest(request);
            // 获取保存的URL
            if (savedRequest == null || savedRequest.getRequestUrl() == null) {
                return "redirect:/";
            } else {
                //String url = savedRequest.getRequestUrl().substring(12, savedRequest.getRequestUrl().length());
            	return "redirect:" + savedRequest.getRequestUrl();
            }
        }

        String username = WebUtils.getCleanParam(request, formAuthenticationFilter.getUsernameParam());
        String password = WebUtils.getCleanParam(request, formAuthenticationFilter.getPasswordParam());
        boolean rememberMe = WebUtils.isTrue(request, formAuthenticationFilter.getRememberMeParam());
        boolean mobile = WebUtils.isTrue(request, formAuthenticationFilter.getMobileLoginParam());
        String message = (String)request.getAttribute(formAuthenticationFilter.getMessageParam());
        String exception = (String)request.getAttribute(MyFormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);

        if (StringUtils.isBlank(message) || StringUtils.equals(message, "null")){
            message = "用户或密码错误, 请重试.";
        }

        model.addAttribute(formAuthenticationFilter.getUsernameParam(), username);
        model.addAttribute(formAuthenticationFilter.getPasswordParam(), password);
        model.addAttribute(formAuthenticationFilter.getRememberMeParam(), rememberMe);
        model.addAttribute(formAuthenticationFilter.getMobileLoginParam(), mobile);
        model.addAttribute(formAuthenticationFilter.getMessageParam(), message);
        model.addAttribute(MyFormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME, exception);

        if (logger.isDebugEnabled()){
            logger.debug("login fail, active session size: {}, message: {}, exception: {}",
                    sessionDAO.getActiveSessions().size(), message, exception);
        }
        
        return "login";
    }
    
}
