package com.mystore.common.meta.domain;

public class AggregateRootMeta<T> extends EntityMeta<T> {

	public AggregateRootMeta(Class<T> clazz,String identityObjectName) {
		super(clazz, identityObjectName);
	}

}
