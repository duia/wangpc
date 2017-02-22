package com.wpc.test.spring;

import org.springframework.context.ApplicationEvent;

public class MyEvent extends ApplicationEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String msg;
	
	public MyEvent(Object source, String msg) {
		super(source);
		this.msg = msg;
	}

	public MyEvent(Object source) {
		super(source);
		// TODO Auto-generated constructor stub
	}

	public void out(){
		System.out.println(this.msg);
	}

}
