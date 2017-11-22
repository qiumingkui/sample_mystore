package com.mystore.common.meta;

import java.lang.reflect.Field;

import com.mystore.common.persistence.Table;

public class EntityMeta<T> extends ClassMeta<T> {

	protected String identityObjectName;

	protected Table<T> table;

	public EntityMeta(Class<T> clazz, String identityObjectName) {
		super(clazz);
		this.identityObjectName = identityObjectName;
	}

	public Field getIdentityObject() {
		return getField(identityObjectName);
	}

	public Table<T> getTable() {
		return table;
	}

	protected void setTable(Table<T> table) {
		this.table = table;
	}

}
