package com.mystore.common.meta.domain;

import com.mystore.common.meta.ClassMeta;
import com.mystore.common.meta.FieldMeta;

public class ValueObjectMeta<T> extends ClassMeta<T> {

	protected String supIdentityObjectName;

	public ValueObjectMeta(Class<T> clazz) {
		super(clazz);
	}

	public FieldMeta<T> getSupIdentityObject() {
		return this.getFieldMeta(supIdentityObjectName);
	}
}
