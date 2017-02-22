package com.wpc.design_patterns.abstract_factory;

import com.wpc.design_patterns.factory_method.Sender;
import com.wpc.design_patterns.factory_method.SmsSender;

public class SmsFactory implements Provider {

	@Override
	public Sender produce() {
		// TODO Auto-generated method stub
		return new SmsSender();
	}

}
