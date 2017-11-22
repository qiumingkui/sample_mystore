package com.mystore.common.meta.domain;

import java.lang.reflect.Field;

import com.mystore.common.meta.ClassMeta;

public abstract class EntityMeta<T> extends ClassMeta<T> implements DomainMeta<T> {

	protected String identityObjectName;

	// protected Table<T> table;

	public EntityMeta(Class<T> clazz, String identityObjectName) {
		super(clazz);
		this.identityObjectName = identityObjectName;
	}

	public Field getIdentityObject() {
		return getField(identityObjectName);
	}

	// public Table<T> getTable() {
	// return table;
	// }
	//
	// protected void setTable(Table<T> table) {
	// this.table = table;
	// }

}
