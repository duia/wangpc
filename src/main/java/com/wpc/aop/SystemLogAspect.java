package com.wpc.aop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wpc.admin.entity.User;
import com.wpc.annotation.SystemLog;
import com.wpc.common.HttpConstant;
import com.wpc.util.date.DateFormatUtils;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Aspect
@Component
public class SystemLogAspect {

    //注入Service用于把日志保存数据库
//    @Resource
//    private LogService logService;

    //本地异常日志记录对象
    private  static  final Logger logger = LoggerFactory.getLogger(SystemLogAspect.class);

    //Service层切点
    @Pointcut("@annotation(com.wpc.annotation.SystemLog)")
    public  void serviceAspect() {
    }

    //Controller层切点
    @Pointcut("@annotation(com.wpc.annotation.SystemLog)")
    public  void controllerAspect() {
    }

    /**
     * 前置通知 用于拦截Controller层记录用户的操作
     *
     * @param joinPoint 切点
     */
    @Before("controllerAspect()")
    public  void doBefore(JoinPoint joinPoint) {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        //读取session中的用户
        User user = (User) session.getAttribute(HttpConstant.LOGIN_USER);
        //请求的IP
        String ip = request.getRemoteAddr();
        try {
            //*========控制台输出=========*//
            System.out.println("=====前置通知开始=====");
            System.out.println("请求方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
            System.out.println("请求方法:" + joinPoint.getSignature().toString());
            SystemLog log = getSystemLog(joinPoint);
            System.out.println("方法描述:" + log.describe());
            System.out.println("操作级别:" + log.operLevel());
            System.out.println("请求IP:" + ip);
            System.out.println("请求人:" + user.getUsername());
            //*========数据库日志=========*//
//            Log log = SpringContextHolder.getBean("logxx");
//            log.setDescription(getControllerMethodDescription(joinPoint));
//            log.setMethod((joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
//            log.setType("0");
//            log.setRequestIp(ip);
//            log.setExceptionCode( null);
//            log.setExceptionDetail( null);
//            log.setParams( null);
//            log.setCreateBy(user);
//            log.setCreateDate(DateUtil.getCurrentDate());
            //保存数据库
//            logService.add(log);
            System.out.println("=====前置通知结束=====");
        }  catch (Exception e) {
            //记录本地异常日志
            logger.error("==前置通知异常==");
            logger.error("异常信息:{}", e.getMessage());
        }
    }

    /**
     * 异常通知 用于拦截service层记录异常日志
     *
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(pointcut = "serviceAspect()", throwing = "e")
    public  void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Session session = SecurityUtils.getSubject().getSession();
        //读取session中的用户
        User user = (User) session.getAttribute(HttpConstant.LOGIN_USER);
        //获取请求ip
        String ip = request.getRemoteAddr();
        //获取用户请求方法的参数并序列化为JSON格式字符串
        String params = "";
        try {
            params = convertArgs(joinPoint.getArgs());
              /*========控制台输出=========*/
            System.out.println("=====异常通知开始=====");
            System.out.println("异常代码:" + e.getClass().getName());
            System.out.println("异常信息:" + e.getMessage());
            System.out.println("异常方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
            System.out.println("方法描述:" + getSystemLog(joinPoint));
            System.out.println("请求人:" + user.getUsername());
            System.out.println("请求IP:" + ip);
            System.out.println("请求参数:" + params);
               /*==========数据库日志=========*/
//            Log log = SpringContextHolder.getBean("logxx");
//            log.setDescription(getServiceMthodDescription(joinPoint));
//            log.setExceptionCode(e.getClass().getName());
//            log.setType("1");
//            log.setExceptionDetail(e.getMessage());
//            log.setMethod((joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
//            log.setParams(params);
//            log.setCreateBy(user);
//            log.setCreateDate(DateUtil.getCurrentDate());
//            log.setRequestIp(ip);
            //保存数据库
//            logService.add(log);
            System.out.println("=====异常通知结束=====");
        }  catch (Exception ex) {
            //记录本地异常日志
            logger.error("==异常通知异常==");
            logger.error("异常信息:{}", ex.getMessage());
        }
         /*==========记录本地异常日志==========*/
//        logger.error("异常方法:{}异常代码:{}异常信息:{}参数:{}", joinPoint.getTarget().getClass().getName() + joinPoint.getSignature().getName(), e.getClass().getName(), e.getMessage(), params);

    }


    /**
     * 获取注解中对方法的描述信息 用于service层注解
     *
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */
    private static SystemLog getSystemLog(JoinPoint joinPoint)
            throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        SystemLog log = null;
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    log = method.getAnnotation(SystemLog.class);
                    break;
                }
            }
        }
        return log;
    }

    /**
     * 转换成相应的日志参数,去掉不需要的参数
     * @param objs 参数对象数组
     * @return
     */
    private String convertArgs(Object[] objs)throws JsonProcessingException {
        String param = "";
        if(null != objs && objs.length >0){
            List para = new ArrayList<>();
            for(int i = 0; i<objs.length;i++){
                Object o = objs[i];
                if(HttpServletRequest.class.isInstance(o)||
                        HttpServletResponse.class.isInstance(o)||
                        HttpSession.class.isInstance(o)||Model.class.isInstance(o)){
                    continue;
                }
                para.add(o);
            }
            ObjectMapper json = new ObjectMapper();
            json.setDateFormat(new SimpleDateFormat(DateFormatUtils.DATE_FORMAT2));
            param = json.writeValueAsString(para);
        }
        return param;
    }

}
