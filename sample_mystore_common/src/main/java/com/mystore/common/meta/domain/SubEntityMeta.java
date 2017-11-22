package com.mystore.common.meta.domain;

import java.lang.reflect.Field;

public class SubEntityMeta<T> extends EntityMeta<T> implements DomainMeta<T> {

	protected String supIdentityObjectName;

	public SubEntityMeta(Class<T> clazz, String identityObjectName, String supIdentityObjectName) {
		super(clazz, identityObjectName);
		this.supIdentityObjectName = supIdentityObjectName;
	}

	public Field getSupIdentityObject() {
		return this.getField(supIdentityObjectName);
	}

}
