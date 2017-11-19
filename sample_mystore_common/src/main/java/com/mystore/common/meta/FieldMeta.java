package com.mystore.common.meta;

public class FieldMeta<T> {

	private String fieldName;
	
	private String fieldType;

	protected String getFieldName() {
		return fieldName;
	}

	protected void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	protected String getFieldType() {
		return fieldType;
	}

	protected void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}
}
