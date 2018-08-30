package com.wpc.test;

import com.baidu.aip.ocr.AipOcr;
import com.wpc.sys.model.User;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wpc.test.spring.MyEvent;

import java.util.HashMap;

public class Test {

//	public static void main(String[] args) {
////		ConfigurableListableBeanFactory bf = new XmlBeanFactory(new ClassPathResource("config/applicationContextTest.xml"));
//		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath*:config/applicationContextTest.xml");
////		BeanFactoryPostProcessor bfpp = (BeanFactoryPostProcessor) bf.getBean("bfpp");
////		bfpp.postProcessBeanFactory(bf);
//		MyEvent me = new MyEvent("Hello", "msg123");
//		ac.publishEvent(me);
//		User user = (User) ac.getBean("a");
//		System.out.println(user.getUsername());
//		DefaultPasswordService passwordService = new DefaultPasswordService();
//		System.out.println(passwordService.encryptPassword("123456"));
//	}
	//设置APPID/AK/SK
	public static final String APP_ID = "10707776";
	public static final String API_KEY = "EqPG8xnZc4g0KNNv9afAvH9O";
	public static final String SECRET_KEY = "GXiEnYw2GX8QuV2AO6gpe1CHwuiwPsiG";

	public static void main(String[] args) {
		// 初始化一个AipOcr
		AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

		// 可选：设置网络连接参数
		client.setConnectionTimeoutInMillis(2000);
		client.setSocketTimeoutInMillis(60000);

		// 可选：设置代理服务器地址, http和socket二选一，或者均不设置
//		client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
//		client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

		// 调用接口
		String path = "C:\\Users\\admin\\Desktop\\captcha-image.jpg";
		JSONObject res = client.basicGeneral(path, new HashMap<String, String>());
		System.out.println(res.toString(2));

	}


}
