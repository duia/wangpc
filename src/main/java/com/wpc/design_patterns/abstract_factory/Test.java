package com.wpc.design_patterns.abstract_factory;

import com.wpc.design_patterns.factory_method.Sender;

/**
 * 
 */
public class Test {

	public static void main(String[] args) {
		Provider p = new MailFactory();
		Sender s = p.produce();
		s.send();
	}
}
