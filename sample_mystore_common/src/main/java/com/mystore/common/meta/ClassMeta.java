package com.mystore.common.meta;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.mystore.common.utils.SimpleBeanUtil;

public class ClassMeta<T> {

	protected Class<T> clazz;

	protected Map<String, Field> fieldMap = new HashMap<String, Field>();

	protected Map<String, Method> methodMap = new HashMap<String, Method>();

	public ClassMeta(Class<T> clazz) {
		super();
		this.clazz = clazz;
	}

	public Class<T> getClazz() {
		return clazz;
	}

	public String getName() {
		return clazz.getName();
	}

	public Field getField(String fieldName) {
		Field field = fieldMap.get(fieldName);
		if (field == null) {
			field = SimpleBeanUtil.getFieldWithSupper(clazz, fieldName);
			if (field != null) {
				fieldMap.put(field.getName(), field);
			}
		}
		return field;
	}

	public Method getMethod(String methodName) {
		return methodMap.get(methodName);
	}
}