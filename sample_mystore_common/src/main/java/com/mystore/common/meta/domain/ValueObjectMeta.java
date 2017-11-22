package com.mystore.common.meta.domain;

public class ValueObjectMeta<T> implements DomainMeta {

	protected String className;
	protected String supIdentityObjectFieldName;

	public ValueObjectMeta(String className, String supIdentityObjectFieldName) {
		super();
		this.className = className;
		this.supIdentityObjectFieldName = supIdentityObjectFieldName;
	}

	public String getClassName() {
		return className;
	}

	public String getSupIdentityObjectFieldName() {
		return supIdentityObjectFieldName;
	}

}
