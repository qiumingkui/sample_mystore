package com.mystore.common.meta;

import java.util.HashMap;
import java.util.Map;

public class ClassMeta<T> {

	protected Class<T> clazz;

	protected Map<String, FieldMeta<T>> fieldMetadataMap = new HashMap<String, FieldMeta<T>>();

	protected Map<String, MethodMeta<T>> methodMetadataMap = new HashMap<String, MethodMeta<T>>();

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

	public String getSimpleName() {
		return clazz.getSimpleName();
	}

	public FieldMeta<T> getFieldMeta(String fieldName) {
		return this.fieldMetadataMap.get(fieldName);
	}

	public MethodMeta<T> getMethodMeta(String methodName) {
		return this.methodMetadataMap.get(methodName);
	}
}