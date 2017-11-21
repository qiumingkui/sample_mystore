package com.mystore.common.meta.domain;

import java.lang.reflect.Field;

import com.mystore.common.meta.ClassMeta;

public class ValueObjectMeta<T> extends ClassMeta<T> {

	protected String supIdentityObjectName;

	public ValueObjectMeta(Class<T> clazz) {
		super(clazz);
	}

	public Field getSupIdentityObject() {
		return this.getField(supIdentityObjectName);
	}
}
