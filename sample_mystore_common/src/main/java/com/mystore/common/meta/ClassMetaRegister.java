package com.mystore.common.meta;

import java.util.HashMap;
import java.util.Map;

public class ClassMetaRegister {

	@SuppressWarnings("rawtypes")
	private Map<String, ClassMeta> classMetaMap = new HashMap<String, ClassMeta>();

	@SuppressWarnings("rawtypes")
	public ClassMeta getClassMeta(String className) {
		return this.classMetaMap.get(className);
	}

	@SuppressWarnings("rawtypes")
	protected void register(ClassMeta classMeta) {
		classMetaMap.put(classMeta.getClassName(), classMeta);
	}
}
