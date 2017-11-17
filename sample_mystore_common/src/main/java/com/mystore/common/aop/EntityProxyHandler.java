package com.mystore.common.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class EntityProxyHandler implements InvocationHandler {

	private Object target;

	public EntityProxyHandler(Object target) {
		
		super();
		
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		before(proxy, method, args);
		
		Object result = method.invoke(target, args);
		
		after(proxy, method, args);
		
		return result;
	}
	
	private void before(Object proxy, Method method, Object[] args){
		
	}
	
	private void after(Object proxy, Method method, Object[] args){
		
	}
}
