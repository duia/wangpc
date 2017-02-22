package com.wpc.design_patterns.factory_method;

/**
 * 普通工厂模式
 * @author admin
 *
 */
public class SendFactory {

	/**
	 * 方法一：根据参数字符串来创建所需要的对象，不足当传进去的字符串参数没有时则会创建对象失败
	 * @param type
	 * @return
	 */
	public Sender produce(String type){
		if("mail".equals(type)){
			return new MailSender();
		}else if("sms".equals(type)){
			return new SmsSender();
		}else{
			return null;
		}
	}
	
	/**
	 * 方法二：见于以上不足可以创建多工厂模式，如下
	 * @return
	 */
	public Sender produceMailSender(){
		return new MailSender();
	}
	public Sender produceSmsSender(){
		return new SmsSender();
	}

	/**
	 * 方法三：可以将以上创建对象的方法用ststic修改，在创建对象时刻省去创建工厂类的过程。
	 * @param args
	 */
	
	public static void main(String[] args) {
		//测试一
//		SendFactory sf = new SendFactory();
//		Sender s = sf.produce("mail");
//		s.send();
		//测试二
		SendFactory sf = new SendFactory();
		Sender s = sf.produceMailSender();
		s.send();
	}
	
	/**
	 * 总体来说，工厂模式适合：凡是出现了大量的产品需要创建，并且具有共同的接口时，可以通过工厂方法模式进行创建。
	 * 在以上的三种模式中，第一种如果传入的字符串有误，不能正确创建对象，第三种相对于第二种，不需要实例化工厂类，所以，大多数情况下，我们会选用第三种——静态工厂方法模式。
	 * 不足：工厂方法模式有一个问题就是，类的创建依赖工厂类，也就是说，如果想要拓展程序，必须对工厂类进行修改，这违背了闭包原则，所以，从设计角度考虑，有一定的问题，如何解决？
	 */
}
