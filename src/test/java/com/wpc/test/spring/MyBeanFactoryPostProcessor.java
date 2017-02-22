package com.wpc.test.spring;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionVisitor;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.util.StringValueResolver;

public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

	private Set<String> set = new HashSet<>();
	
	public MyBeanFactoryPostProcessor() {
		super();
	}

	@Override
	public String toString() {
		return "MyBeanFactoryPostProcessor [set=" + set + "]";
	}

	public Set<String> getSet() {
		return set;
	}

	public void setSet(Set<String> set) {
		this.set.clear();
		for (String string : set) {
			this.set.add(string.toUpperCase());
		}
	}

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		// TODO Auto-generated method stub
		String[] beanNames = beanFactory.getBeanDefinitionNames();
		for (String string : beanNames) {
			BeanDefinition  bd = beanFactory.getBeanDefinition(string);
			StringValueResolver valueResolver = new StringValueResolver() {
				
				@Override
				public String resolveStringValue(String strVal) {
					// TODO Auto-generated method stub
					if(isContains(strVal)) return "*****";
					return strVal;
				}
			};
			BeanDefinitionVisitor bdv = new BeanDefinitionVisitor(valueResolver);
			bdv.visitBeanDefinition(bd);
		}
	}
	
	private boolean isContains(Object obj){
		String s = obj.toString().toUpperCase();
		return this.set.contains(s);
	}

}
