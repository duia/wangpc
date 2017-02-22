/**  
 * @Title:  MailTest.java   
 * @Package com.wpc.test.spring   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: wangpengcheng     
 * @date:   2016年12月2日 下午5:54:25   
 * @version V1.0 
 */
package com.wpc.test.spring;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

/**   
 * @ClassName:  MailTest   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: wangpengcheng 
 * @date:   2016年12月2日 下午5:54:25   
 *     
 */
public class MailTest {

	public static void main(String[] args) throws Exception {
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath*:config/applicationContextTest.xml");
		JavaMailSenderImpl mailSender = (JavaMailSenderImpl) ac.getBean("mailSender");
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true,"UTF-8");
		helper.setFrom("wpcfree@qq.com");  
		helper.setTo("376205421@qq.com");  
		helper.setSubject("邮箱测试");  
		helper.setText("test,中文测试");
        mailSender.send(message);
	}
}
