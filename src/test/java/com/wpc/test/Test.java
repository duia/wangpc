package com.wpc.test;

import com.wpc.sys.model.User;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wpc.test.spring.MyEvent;

public class Test {

	public static void main(String[] args) {
////		ConfigurableListableBeanFactory bf = new XmlBeanFactory(new ClassPathResource("config/applicationContextTest.xml"));
//		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath*:config/applicationContextTest.xml");
////		BeanFactoryPostProcessor bfpp = (BeanFactoryPostProcessor) bf.getBean("bfpp");
////		bfpp.postProcessBeanFactory(bf);
//		MyEvent me = new MyEvent("Hello", "msg123");
//		ac.publishEvent(me);
//		User user = (User) ac.getBean("a");
//		System.out.println(user.getUsername());
		DefaultPasswordService passwordService = new DefaultPasswordService();
		System.out.println(passwordService.encryptPassword("123456"));
	}

}
