package com.mystore.common.meta.domain;

import java.util.HashMap;
import java.util.Map;

public class SubEntityMetaRegister {

	@SuppressWarnings("rawtypes")
	private Map<String, SubEntityMeta> subEntityMetaMap = new HashMap<String, SubEntityMeta>();

	@SuppressWarnings("rawtypes")
	public SubEntityMeta getSubEntityMeta(String className) {
		return this.subEntityMetaMap.get(className);
	}

	@SuppressWarnings("rawtypes")
	public void register(SubEntityMeta classMeta) {
		subEntityMetaMap.put(classMeta.getName(), classMeta);
	}
}
