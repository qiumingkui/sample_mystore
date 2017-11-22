package com.mystore.common.meta.domain;

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
	protected void register(AggregateRootMeta aggregateRootMeta) {
		aggregateRootMetaMap.put(aggregateRootMeta.getClassName(), aggregateRootMeta);
	}
}
