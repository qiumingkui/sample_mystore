package com.mystore.common.meta.domain;

import java.util.HashMap;
import java.util.Map;

public class EntityMetaRegister {

	@SuppressWarnings("rawtypes")
	private Map<String, EntityMeta> entityMetaMap = new HashMap<String, EntityMeta>();

	@SuppressWarnings("rawtypes")
	public EntityMeta getEntityMeta(String className) {
		return this.entityMetaMap.get(className);
	}

	@SuppressWarnings("rawtypes")
	protected void register(EntityMeta classMeta) {
		entityMetaMap.put(classMeta.getClassName(), classMeta);
	}
}
