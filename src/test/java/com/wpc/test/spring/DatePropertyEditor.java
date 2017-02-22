package com.wpc.test.spring;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.beans.propertyeditors.CustomDateEditor;

public class DatePropertyEditor implements PropertyEditorRegistrar{

	@Override
	public void registerCustomEditors(PropertyEditorRegistry registry) {
		// TODO Auto-generated method stub
		registry.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}


}
