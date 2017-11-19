package com.mystore.common.persistence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public abstract class TableBase<T> {

	private Class<T> clazz;

	private String name;

	protected Map<String, Column<T>> map = new HashMap<String, Column<T>>();

	public Class<T> getClazz() {
		return clazz;
	}

	protected void setClazz(Class<T> clazz) {
		this.clazz = clazz;
	}

	public String name() {
		return name;
	}

	public Column<T> column(Object key) {
		return map.get(key);
	}

	public Collection<Column<T>> columns() {
		Collection<Column<T>> values = new ArrayList<Column<T>>(map.values());
		return values;
	}

	protected void setTableName(String name) {
		this.name = name;
	}

	public Column<T> primaryKey() {
		for (Column<T> column : map.values()) {
			if (column.isPrimaryKay())
				return column;
		}
		return null;
	}

	public Column<T> foreignKey() {
		for (Column<T> column : map.values()) {
			if (column.isForeignKey())
				return column;
		}
		return null;
	}

	public Column<T> version() {
		for (Column<T> column : map.values()) {
			if (column.isVersion())
				return column;
		}
		return null;
	}

}
