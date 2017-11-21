package com.mystore.common.persistence;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mystore.common.utils.SimpleBeanUtil;

public abstract class Table<T> extends TableBase<T> {

	public Table() {
		super();
		init();
	}

	protected abstract void init();

	protected Column<T> add(String columnName, String fieldPath, Column2PsValueSetter<T> psSetter,
			Rs2ColumnValueSetter<T> rsSetter) {
		Column<T> column = new Column<T>();
		column.setColumnName(columnName);
		column.setFieldPath(fieldPath);
		column.setPsSetter(psSetter);
		column.setRsSetter(rsSetter);
		map.put(column.getColumnName(), column);
		return column;
	}

	protected Column<T> add(String columnName, Column2PsValueSetter<T> psSetter, Rs2ColumnValueSetter<T> rsSetter) {
		Column<T> column = new Column<T>();
		column.setColumnName(columnName);
		column.setPsSetter(psSetter);
		column.setRsSetter(rsSetter);
		map.put(column.getColumnName(), column);
		return column;
	}

	protected Column<T> add(String columnName, String fieldPath) {
		Column<T> column = new Column<T>();
		column.setColumnName(columnName);
		column.setFieldPath(fieldPath);
		column.setPsSetter(
				(PreparedStatement ps, int index, T object) -> ps.setObject(index, getFieldPathValue(object, fieldPath)));
		column.setRsSetter((T object, ResultSet rs) -> setFieldPathValue(object, fieldPath, rs.getObject(columnName)));
		map.put(column.getColumnName(), column);
		return column;
	}

	// protected Column<T> add(String columnAndFieldName) {
	// add(columnAndFieldName, columnAndFieldName);
	// return this.column(columnAndFieldName);
	// }

	protected void setPrimaryKay(String columnName) {
		map.get(columnName).setPrimaryKay();
	}

	protected void setForeignKey(String columnName) {
		map.get(columnName).setForeignKey();
	}

	protected void setVersion(String columnName) {
		map.get(columnName).setVersion();
	}

	public static void setFieldPathValue(Object object, String fieldPath, Object value) throws IllegalAccessException,
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

	public static Object getFieldPathValue(Object object, String fieldPath) throws IllegalAccessException,
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
}
