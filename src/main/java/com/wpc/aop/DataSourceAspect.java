package com.wpc.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DataSourceAspect {

    //Service层切点
    @Pointcut("execution(* com.wpc..service.*.*(..)) && @annotation(com.wpc.annotation.SysLogAnn)")
    public  void serviceAspect() {
    }

    @Before("serviceAspect()")
    public void setDynamicDataSource(JoinPoint jp) {
        for(Object o : jp.getArgs()) {
            //处理具体的逻辑 ，根据具体的境况CustomerContextHolder.setCustomerType()选取DataSource
            System.out.println(o);
        }
    }
}
