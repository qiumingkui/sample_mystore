package com.mystore.common.meta;

import java.lang.reflect.Method;

public class MethodMeta<T> {

	protected String methodName;
	
	protected Method method;

	public String getMethodName() {
		return methodName;
	}

	public Method getMethod() {
		return method;
	}
}
