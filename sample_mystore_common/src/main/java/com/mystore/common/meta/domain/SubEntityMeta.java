package com.mystore.common.meta.domain;

public class SubEntityMeta<T> extends EntityMeta implements DomainMeta {

	protected String supIdentityObjectFieldName;

	public SubEntityMeta(String className, String identityObjectFieldName, String supIdentityObjectFieldName) {
		super(className, identityObjectFieldName);
		this.supIdentityObjectFieldName = supIdentityObjectFieldName;
	}

	public String getSupIdentityObjectFieldName() {
		return supIdentityObjectFieldName;
	}

}
