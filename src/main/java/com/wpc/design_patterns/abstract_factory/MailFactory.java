package com.wpc.design_patterns.abstract_factory;

import com.wpc.design_patterns.factory_method.MailSender;
import com.wpc.design_patterns.factory_method.Sender;

public class MailFactory implements Provider {

	@Override
	public Sender produce() {
		// TODO Auto-generated method stub
		return new MailSender();
	}

}
