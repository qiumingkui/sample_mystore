package com.mystore.common.meta.domain;

public abstract class EntityMeta implements DomainMeta {

	protected String className;

	protected String identityObjectFieldName;

	public EntityMeta(String className, String identityObjectFieldName) {
		super();
		this.className = className;
		this.identityObjectFieldName = identityObjectFieldName;
	}

	public String getClassName() {
		return className;
	}

	public String getIdentityObjectFieldName() {
		return identityObjectFieldName;
	}

}
