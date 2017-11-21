package com.mystore.common.meta;

import java.lang.reflect.Field;

public class EntityMeta<T> extends ClassMeta<T> {

	protected String identityObjectName;
	
	public EntityMeta(Class<T> clazz,String identityObjectName) {
		super(clazz);
		setIdentityObjectName(identityObjectName);
	}

	public Field getIdentityObject(){
		return getField(identityObjectName);
	}

	protected void setIdentityObjectName(String identityObjectName) {
		this.identityObjectName = identityObjectName;
	}
	
	
}
