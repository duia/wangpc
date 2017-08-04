package com.wpc.aop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wpc.admin.entity.User;
import com.wpc.annotation.SysLogAnn;
import com.wpc.common.DataSourceContextHolder;
import com.wpc.common.HttpConstant;
import com.wpc.enums.DataSource;
import com.wpc.enums.OperType;
import com.wpc.sys.dao.SysLogDao;
import com.wpc.sys.model.SysLog;
import com.wpc.sys.service.SysLogService;
import com.wpc.util.date.DateFormatUtils;
import com.wpc.util.exception.Exceptions;
import com.wpc.util.net.IpUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NamedThreadLocal;
import org.springframework.core.annotation.Order;
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
import java.util.Date;
import java.util.List;

@Aspect
@Component
public class SystemLogAspect {

    //注入Service用于把日志保存数据库
    @Autowired
    private SysLogService sysLogService;

    private static final ThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<Long>("ThreadLocal StartTime");

    //本地异常日志记录对象
    private static  final Logger logger = LoggerFactory.getLogger(SystemLogAspect.class);

    //Service层切点
    @Pointcut("execution(* com.wpc..service.*.*(..)) && @annotation(com.wpc.annotation.SysLogAnn)")
    public  void serviceAspect() {
    }

    //Controller层切点
    @Pointcut("execution(* com.wpc..controller.*.*(..)) && @annotation(com.wpc.annotation.SysLogAnn)")
    public void controllerAspect() {
    }

    /**
     * 前置通知 用于拦截Controller层记录用户的操作
     *
     */
    @Before("controllerAspect()")
    public void doBefore() throws Exception {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        if (logger.isDebugEnabled()){
            long beginTime = System.currentTimeMillis();//1、开始时间
            startTimeThreadLocal.set(beginTime);		//线程绑定变量（该数据只有当前请求的线程可见）
            logger.debug("开始计时: {}  URI: {}", new SimpleDateFormat("hh:mm:ss.SSS")
                    .format(beginTime), request.getRequestURI());
        }
    }

    @Around("controllerAspect()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object modelAndView = joinPoint.proceed();
        if (modelAndView != null){
            logger.info("ViewName: " + modelAndView.toString());
        }
        return modelAndView;
    }

    /**
     * 后置通知 用于拦截Controller层记录用户的操作
     *
     * @param joinPoint 切点
     */
    @AfterReturning("controllerAspect()")
    public void doAfter(JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        // 保存日志
        try {
            saveLog(joinPoint);
            // 打印JVM信息。
            if (logger.isDebugEnabled()){
                long beginTime = startTimeThreadLocal.get();//得到线程绑定的局部变量（开始时间）
                long endTime = System.currentTimeMillis(); 	//2、结束时间
                logger.debug("计时结束：{}  耗时：{}  URI: {}  最大内存: {}m  已分配内存: {}m  已分配内存中的剩余空间: {}m  最大可用内存: {}m",
                        new SimpleDateFormat("hh:mm:ss.SSS").format(endTime), DateFormatUtils.formatDateTime(endTime - beginTime),
                        request.getRequestURI(), Runtime.getRuntime().maxMemory()/1024/1024, Runtime.getRuntime().totalMemory()/1024/1024, Runtime.getRuntime().freeMemory()/1024/1024,
                        (Runtime.getRuntime().maxMemory()-Runtime.getRuntime().totalMemory()+Runtime.getRuntime().freeMemory())/1024/1024);
                //删除线程变量中的数据，防止内存泄漏
                startTimeThreadLocal.remove();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 异常通知 用于拦截service层记录异常日志
     *
     * @param joinPoint
     * @param ex
     */
    @AfterThrowing(pointcut = "controllerAspect()", throwing = "ex")
    public void doAfterThrowing(JoinPoint joinPoint, Exception ex) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        // 保存日志
        try {
            saveLog(joinPoint, ex);
            // 打印JVM信息。
            if (logger.isDebugEnabled()){
                long beginTime = startTimeThreadLocal.get();//得到线程绑定的局部变量（开始时间）
                long endTime = System.currentTimeMillis(); 	//2、结束时间
                logger.debug("计时结束：{}  耗时：{}  URI: {}  最大内存: {}m  已分配内存: {}m  已分配内存中的剩余空间: {}m  最大可用内存: {}m",
                        new SimpleDateFormat("hh:mm:ss.SSS").format(endTime), DateFormatUtils.formatDateTime(endTime - beginTime),
                        request.getRequestURI(), Runtime.getRuntime().maxMemory()/1024/1024, Runtime.getRuntime().totalMemory()/1024/1024, Runtime.getRuntime().freeMemory()/1024/1024,
                        (Runtime.getRuntime().maxMemory()-Runtime.getRuntime().totalMemory()+Runtime.getRuntime().freeMemory())/1024/1024);
                //删除线程变量中的数据，防止内存泄漏
                startTimeThreadLocal.remove();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 获取注解中对方法的描述信息 用于service层注解
     *
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */
    private SysLogAnn getSystemLog(JoinPoint joinPoint) throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        SysLogAnn log = null;
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    log = method.getAnnotation(SysLogAnn.class);
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

    /**
     * 保存日志
     */
    public void saveLog(JoinPoint joinPoint) throws Exception {
        saveLog(joinPoint, null);
    }

    /**
     * 保存日志
     */
    public void saveLog(JoinPoint joinPoint, Exception ex) throws Exception {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Session session = SecurityUtils.getSubject().getSession();
        //读取session中的用户
        User user = (User) session.getAttribute(HttpConstant.LOGIN_USER);
        if (user != null && user.getId() != null){
            SysLogAnn logAnn = getSystemLog(joinPoint);
            SysLog log = new SysLog();
            log.setOperLevel(logAnn.operLevel().getValue());
            log.setOperType(ex == null ? logAnn.operType().getValue() : OperType.EXCEPTION.getValue());
            log.setOperDescribe(logAnn.describe());
            log.setOperName(joinPoint.getSignature().toString());
            log.setOperParam(convertArgs(joinPoint.getArgs()));
            log.setOperTime(new Date());
            log.setUserId(user.getId());
            log.setUserName(user.getUsername());
            log.setRemoteAddr(IpUtils.getIpAddress(request));
            log.setUserAgent(request.getHeader("user-agent"));
            log.setRequestUri(request.getRequestURI());
            log.setMethod(request.getMethod());
            // 异步保存日志
            new SaveLogThread(log, ex).start();
        }
    }

    /**
     * 保存日志线程
     */
    public class SaveLogThread extends Thread{

        private SysLog log;
        private Exception ex;

        public SaveLogThread(SysLog log, Exception ex){
            super(SaveLogThread.class.getSimpleName());
            this.log = log;
            this.ex = ex;
        }

        @Override
        public void run() {
            // 如果有异常，设置异常信息
            log.setException(Exceptions.getStackTraceAsString(ex));
            // 如果无标题并无异常日志，则不保存信息
            if (StringUtils.isBlank(log.getOperName()) && StringUtils.isBlank(log.getException())){
                return;
            }
            // 保存日志信息
            DataSourceContextHolder.setDataSourceType("sys");
//            int i = 1/0;
            sysLogService.save(log);
//            System.out.println(log);
        }
    }

}
