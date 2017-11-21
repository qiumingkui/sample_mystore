package com.mystore.common.meta;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.mystore.common.utils.SimpleBeanUtil;

public class ClassMeta<T> {

	protected Class<T> clazz;

	// protected Map<String, FieldMeta<T>> fieldMetadataMap = new
	// HashMap<String, FieldMeta<T>>();
	//
	// protected Map<String, MethodMeta<T>> methodMetadataMap = new
	// HashMap<String, MethodMeta<T>>();

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

	// public String getSimpleName() {
	// return clazz.getSimpleName();
	// }
	//
	// public FieldMeta<T> getFieldMeta(String fieldName) {
	// return this.fieldMetadataMap.get(fieldName);
	// }
	//
	// public MethodMeta<T> getMethodMeta(String methodName) {
	// return this.methodMetadataMap.get(methodName);
	// }
	//
	// public void putFieldMeta(FieldMeta<T> fieldMeta) {
	// this.fieldMetadataMap.put(fieldMeta.getName(), fieldMeta);
	// }
	//
	// public void putFieldMeta(MethodMeta<T> methodMeta) {
	// this.methodMetadataMap.put(methodMeta.getName(), methodMeta);
	// }
}