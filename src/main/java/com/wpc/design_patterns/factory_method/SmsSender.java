package com.wpc.design_patterns.factory_method;

public class SmsSender implements Sender {

	@Override
	public void send() {
		// TODO Auto-generated method stub
		System.out.println("Sms发送");
	}

}
