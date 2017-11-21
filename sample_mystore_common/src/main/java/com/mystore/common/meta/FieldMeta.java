package com.mystore.common.meta;

import java.lang.reflect.Field;

public class FieldMeta<T> {

	protected String fieldName;
	
	protected Field field;

	public String getFieldName() {
		return fieldName;
	}

	public Field getField() {
		return field;
	}
}
