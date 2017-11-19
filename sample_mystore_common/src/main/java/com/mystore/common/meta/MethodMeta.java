package com.mystore.common.meta;

import java.util.Collection;

public class MethodMeta<T> {

	private String methodName;

	private Collection<Interceptor> interceptors;

	protected String getMethodName() {
		return methodName;
	}

	protected void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	protected Collection<Interceptor> getInterceptors() {
		return interceptors;
	}

	protected void setInterceptors(Collection<Interceptor> interceptors) {
		this.interceptors = interceptors;
	}

}
