package com.wpc.test.spring;

import org.springframework.context.ApplicationListener;

public class MyListener implements ApplicationListener<MyEvent> {

	@Override
	public void onApplicationEvent(MyEvent event) {
		// TODO Auto-generated method stub
		event.out();
	}

}
