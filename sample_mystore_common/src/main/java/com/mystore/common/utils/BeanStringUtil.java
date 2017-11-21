package com.mystore.common.utils;

public class BeanStringUtil {

	public static String convertClassNameToFieldName(String simpleClassName) {
		String fieldName = simpleClassName;
		String first = simpleClassName.substring(0, 1);
		String temp = simpleClassName.substring(1, fieldName.length());
		first = first.toLowerCase();
		fieldName = first + temp;
		return fieldName;
	}

}
