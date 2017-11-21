package com.mystore.common.meta;

import java.lang.reflect.Field;

public class ValueObjectMeta<T> extends ClassMeta<T> {

	protected String supIdentityObjectName;

	public ValueObjectMeta(Class<T> clazz) {
		super(clazz);
	}

	public Field getSupIdentityObject() {
		return this.getField(supIdentityObjectName);
	}
}
