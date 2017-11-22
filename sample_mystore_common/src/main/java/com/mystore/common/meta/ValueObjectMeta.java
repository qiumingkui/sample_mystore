package com.mystore.common.meta;

import java.lang.reflect.Field;

import com.mystore.common.persistence.Table;

public class ValueObjectMeta<T> extends ClassMeta<T> {

	protected String supIdentityObjectName;

	protected Table<T> table;

	public ValueObjectMeta(Class<T> clazz, String supIdentityObjectName) {
		super(clazz);
		this.supIdentityObjectName = supIdentityObjectName;
	}

	public Field getSupIdentityObject() {
		return this.getField(supIdentityObjectName);
	}

	public Table<T> getTable() {
		return table;
	}

	protected void setTable(Table<T> table) {
		this.table = table;
	}
}
