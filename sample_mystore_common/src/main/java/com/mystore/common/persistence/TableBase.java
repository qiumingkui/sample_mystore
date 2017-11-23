package com.mystore.common.persistence;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.mystore.common.utils.SimpleBeanUtil;

public abstract class TableBase<T> {

	private String className;

	private String tableName;

	protected Map<String, Column<T>> map = new HashMap<String, Column<T>>();

	public String getClassName() {
		return className;
	}

	protected void setClassName(String className) {
		this.className = className;
	}

	public String getTableName() {
		return tableName;
	}

	protected void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public Column<T> getColumn(Object key) {
		return map.get(key);
	}

	public Collection<Column<T>> values() {
		Collection<Column<T>> values = new ArrayList<Column<T>>(map.values());
		return values;
	}

	public Collection<Column<T>> getColumnsByFieldName(String fieldName) {
		Collection<Column<T>> collection = new ArrayList<Column<T>>();

		Collection<Column<T>> values = values();
		for (Column<T> column : values) {
			String fieldPath = column.getFieldPath();
			String[] fields = splitFieldPath(fieldPath);
			if (fields[0].equals(fieldName)) {
				collection.add(column);
			}
		}

		return collection;
	}

	// public String getIdFieldName() {
	// if (getPrimaryKey() != null) {
	// String fieldPath = getPrimaryKey().getFieldPath();
	// String[] fields = splitFieldPath(fieldPath);
	// return fields[0];
	// }
	// return null;
	// }

	public Column<T> getPrimaryKey() {
		for (Column<T> column : map.values()) {
			if (column.isPrimaryKay())
				return column;
		}
		return null;
	}

	protected void setPrimaryKay(String columnName) {
		map.get(columnName).setPrimaryKay();
	}

	public Column<T> getForeignKey() {
		for (Column<T> column : map.values()) {
			if (column.isForeignKey())
				return column;
		}
		return null;
	}

	protected void setForeignKey(String columnName) {
		map.get(columnName).setForeignKey();
	}

	public Column<T> getVersion() {
		for (Column<T> column : map.values()) {
			if (column.isVersion())
				return column;
		}
		return null;
	}

	protected void setVersion(String columnName) {
		map.get(columnName).setVersion();
	}

	protected void setFieldPathValue(Object object, String fieldPath, Object value) throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException, NoSuchFieldException, SecurityException {
		String[] fieldNames = fieldPath.split("\\.");
		Object pathObject = object;
		for (int i = 0; i < fieldNames.length; i++) {
			String fieldName = fieldNames[i];
			if (i == fieldNames.length - 1) {
				setFieldValue(pathObject, fieldName, value);
				return;
			}

			Object temp = getFieldValue(pathObject, fieldName);
			if (temp == null) {
				Field field = SimpleBeanUtil.getFieldWithSupper(pathObject.getClass(), fieldName);
				temp = SimpleBeanUtil.newInstance(field.getType());
				setFieldValue(pathObject, fieldName, temp);
			}
			pathObject = temp;
		}
	}

	protected Object getFieldPathValue(Object object, String fieldPath) throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException, NoSuchFieldException, SecurityException {
		String[] fieldNames = fieldPath.split("\\.");
		Object pathObject = object;
		for (int i = 0; i < fieldNames.length; i++) {
			String fieldName = fieldNames[i];
			pathObject = getFieldValue(pathObject, fieldName);
			if (pathObject == null) {
				return null;
			}
			if (i == fieldNames.length - 1) {
				return pathObject;
			}
		}
		return null;
	}

	private static void setFieldValue(Object object, String fieldName, Object value) throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException, NoSuchFieldException, SecurityException {
		Field field = SimpleBeanUtil.getFieldWithSupper(object.getClass(), fieldName);
		field.setAccessible(true);
		field.set(object, value);
	}

	private static Object getFieldValue(Object object, String fieldName) throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException, NoSuchFieldException, SecurityException {
		Field field = SimpleBeanUtil.getFieldWithSupper(object.getClass(), fieldName);
		field.setAccessible(true);
		return field.get(object);
	}

	private String[] splitFieldPath(String fieldPath) {
		String[] fields = fieldPath.split("\\.");
		return fields;
	}

}
