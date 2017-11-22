package com.mystore.common.meta;

import java.util.HashMap;
import java.util.Map;

import com.mystore.common.persistence.Table;

public class TableRegister {

	@SuppressWarnings("rawtypes")
	private Map<String, Table> tableMap = new HashMap<String, Table>();

	@SuppressWarnings("rawtypes")
	public Table getTable(String className) {
		return this.tableMap.get(className);
	}

	@SuppressWarnings("rawtypes")
	public void register(Table table) {
		tableMap.put(table.getClassName(), table);
	}
}
