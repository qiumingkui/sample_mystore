package com.mystore.common.meta.domain;

import java.util.HashMap;
import java.util.Map;

public class ValueObjectMetaRegister {

	@SuppressWarnings("rawtypes")
	private Map<String, ValueObjectMeta> valueObjectMetaMap = new HashMap<String, ValueObjectMeta>();

	@SuppressWarnings("rawtypes")
	public ValueObjectMeta getValueObjectMeta(String className) {
		return this.valueObjectMetaMap.get(className);
	}

	@SuppressWarnings("rawtypes")
	protected void register(ValueObjectMeta classMeta) {
		valueObjectMetaMap.put(classMeta.getSimpleName(), classMeta);
	}
}
