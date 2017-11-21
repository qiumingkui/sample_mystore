package com.mystore.common.meta;

public class AggregateRootMeta<T> extends EntityMeta<T> {

	public AggregateRootMeta(Class<T> clazz,String identityObjectName) {
		super(clazz, identityObjectName);
	}

}
