package com.mystore.common.persistence;

import java.util.HashMap;
import java.util.Map;

public class Table<T> {
	private String name;
	private Map<String, Column<T>> collection = new HashMap<String, Column<T>>();

	public void put(String key,Column<T> column){
		collection.put(key, column);
	}
	
	public Column<T> get(String key){
		return collection.get(key);
	}

	public String name() {
		return name;
	}

	public void changeName(String name) {
		this.name = name;
	}
	
	
}
