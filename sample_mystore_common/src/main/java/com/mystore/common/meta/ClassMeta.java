package com.mystore.common.meta;

import java.util.Collection;

import com.mystore.common.persistence.Table;

public class ClassMeta<T> {

	private DomainElementType domainElementType = DomainElementType.NOT_DOMAIN;

	private String className;

	private Table<T> table;

	private Collection<FieldMeta<T>> fieldMetadatas;

	private Collection<MethodMeta<T>> MethodMetadatas;

	protected DomainElementType getDomainElementType() {
		return domainElementType;
	}

	protected void setDomainElementType(DomainElementType domainElementType) {
		this.domainElementType = domainElementType;
	}

	protected String getClassName() {
		return className;
	}

	protected void setClassName(String className) {
		this.className = className;
	}

	protected Table<T> getTable() {
		return table;
	}

	protected void setTable(Table<T> table) {
		this.table = table;
	}

	protected Collection<FieldMeta<T>> getFieldMetadatas() {
		return fieldMetadatas;
	}

	protected void setFieldMetadatas(Collection<FieldMeta<T>> fieldMetadatas) {
		this.fieldMetadatas = fieldMetadatas;
	}

	protected Collection<MethodMeta<T>> getMethodMetadatas() {
		return MethodMetadatas;
	}

	protected void setMethodMetadatas(Collection<MethodMeta<T>> methodMetadatas) {
		MethodMetadatas = methodMetadatas;
	}

}
