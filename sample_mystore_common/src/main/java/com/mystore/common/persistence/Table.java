package com.mystore.common.persistence;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

public class Table<T> extends HashMap<String, Column<T>> {

	private static final long serialVersionUID = 1L;
	private String name;

	// private Map<String, Column<T>> map = new HashMap<String, Column<T>>();

	public void changeName(String name) {
		this.name = name;
	}

	public String name() {
		return name;
	}

	public Collection<Column<T>> multiPrimaryKeyCollection() {
		Collection<Column<T>> primaryKeys = new HashSet<Column<T>>();
		for (Column<T> column : this.values()) {
			if (column.isPrimaryKay())
				primaryKeys.add(column);
		}
		return primaryKeys;
	}

	public Column<T> primaryKey() {
		for (Column<T> column : this.values()) {
			if (column.isPrimaryKay())
				return column;
		}
		return null;
	}

	
	public Column<T> version() {
		for (Column<T> column : this.values()) {
			if (column.isVersion())
				return column;
		}
		return null;
	}

	// public void put(String key,Column<T> column){
	// map.put(key, column);
	// }
	//
	// public Column<T> get(String key){
	// return map.get(key);
	// }
	//
	// public Set<String> keySet(){
	// return map.keySet();
	// }
	//
	// public Collection<Column<T>> values(){
	// return map.values();
	// }

}
