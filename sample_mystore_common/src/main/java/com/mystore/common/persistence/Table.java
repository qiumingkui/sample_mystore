package com.mystore.common.persistence;

import java.util.HashMap;

public class Table<V> extends HashMap<String,V> {


	private static final long serialVersionUID = 1L;
	private String name;

	//	private Map<String, Column<T>> map = new HashMap<String, Column<T>>();

	public void changeName(String name) {
		this.name = name;
	}

	public String name() {
		return name;
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
