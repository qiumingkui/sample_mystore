package com.mystore.common.meta;

import java.util.HashMap;
import java.util.Map;

public class AggregateRootMetaRegister {

	@SuppressWarnings("rawtypes")
	private Map<String, AggregateRootMeta> aggregateRootMetaMap = new HashMap<String, AggregateRootMeta>();

	@SuppressWarnings("rawtypes")
	public AggregateRootMeta getAggregateRootMeta(String className) {
		return this.aggregateRootMetaMap.get(className);
	}

	@SuppressWarnings("rawtypes")
	protected void register(AggregateRootMeta classMeta) {
		aggregateRootMetaMap.put(classMeta.getName(), classMeta);
	}
}
