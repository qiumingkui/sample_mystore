package com.mystore.common.meta.domain;

import java.lang.reflect.Field;

import com.mystore.common.meta.ClassMeta;

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
