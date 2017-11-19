package com.mystore.common.meta;

public enum DomainElementType {

	NOT_DOMAIN(-1), AGGREGATE_ROOT(0), ENTITY(1), OBJECT_VALUE(2),;

	private int code;

	private DomainElementType(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
}
