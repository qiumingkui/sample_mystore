package com.mystore.common.persistence;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

public abstract class Table<T> extends HashMap<String, Column<T>> {

	private static final long serialVersionUID = 1L;
	private String name;

	// private Map<String, Column<T>> map = new HashMap<String, Column<T>>();
	public Table() {
		super();
		init();
	}

	public String name() {
		return name;
	}
	
	protected void setName(String name){
		this.name=name;
	}
	
	protected abstract void init();

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

	protected Column<T> add(String name, PSSetter<T> psSetter, RSSetter<T> rsSetter) {
		Column<T> column = new Column<T>();
		column.setName(name);
		column.setPsSetter(psSetter);
		column.setRsSetter(rsSetter);
		this.put(column.name(), column);
		return column;
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
