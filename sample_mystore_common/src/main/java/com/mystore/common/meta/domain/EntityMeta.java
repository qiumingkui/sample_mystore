package com.mystore.common.meta.domain;

import com.mystore.common.meta.ClassMeta;
import com.mystore.common.meta.FieldMeta;

public class EntityMeta<T> extends ClassMeta<T> {

	protected String identityObjectName;
	
	public FieldMeta<T> getIdentityObjectMeta(){
		return getFieldMeta(identityObjectName);
	}
}
