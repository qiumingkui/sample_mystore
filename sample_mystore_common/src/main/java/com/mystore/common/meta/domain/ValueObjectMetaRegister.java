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
	public void register(ValueObjectMeta valueObjectMeta) {
		valueObjectMetaMap.put(valueObjectMeta.getClassName(), valueObjectMeta);
	}
}
