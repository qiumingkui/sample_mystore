package com.mystore.common.meta;

import com.mystore.common.meta.domain.AggregateRootMeta;
import com.mystore.common.meta.domain.SubEntityMeta;
import com.mystore.common.meta.domain.ValueObjectMeta;
import com.mystore.common.persistence.Table;

public abstract class MetaFactory {

	protected RegisterCenter registerCenter = RegisterCenter.instance();

	public MetaFactory() {
		init();
	}

	abstract protected void init();

	// protected RegisterCenter getRegisterCenter() {
	// return registerCenter;
	// }

	@SuppressWarnings("rawtypes")
	public AggregateRootMeta getAggregateRootMeta(String className) {
		AggregateRootMeta aggregateRootMeta = registerCenter.getAggregateRootMetaRegister()
				.getAggregateRootMeta(className);
		return aggregateRootMeta;
	}

	@SuppressWarnings("rawtypes")
	public SubEntityMeta getSubEntityMeta(String className) {
		SubEntityMeta subEntityMeta = registerCenter.getSubEntityMetaRegister().getSubEntityMeta(className);
		return subEntityMeta;
	}

	@SuppressWarnings("rawtypes")
	public ValueObjectMeta getValueObjectMeta(String className) {
		ValueObjectMeta valueObjectMeta = registerCenter.getValueObjectMetaRegister().getValueObjectMeta(className);
		return valueObjectMeta;
	}

	@SuppressWarnings("rawtypes")
	public ClassMeta getClassMeta(String className) {
		ClassMeta classMeta = registerCenter.getClassMetaRegister().getClassMeta(className);
		return classMeta;
	}

	@SuppressWarnings("rawtypes")
	public Table getTable(String className) {
		Table table = registerCenter.getTableRegister().getTable(className);
		return table;
	}
}