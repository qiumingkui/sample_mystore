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

	protected Column<T> add(String columnName, String fieldName, Column2PsValueSetter<T> psSetter,
			Rs2ColumnValueSetter<T> rsSetter) {
		Column<T> column = new Column<T>();
		column.setColumnName(columnName);
		column.setFieldName(fieldName);
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

	protected Column<T> add(String columnName, String fieldName) {
		Column<T> column = new Column<T>();
		column.setColumnName(columnName);
		column.setFieldName(fieldName);
		column.setPsSetter(
				(PreparedStatement ps, int index, T object) -> ps.setObject(index, getFieldValue(object, fieldName)));
		column.setRsSetter((T object, ResultSet rs) -> setFieldValue(object, fieldName, rs.getObject(columnName)));
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

	public static void setFieldValue(Object object, String fieldName, Object value) throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException, NoSuchFieldException, SecurityException {
		// Field field = object.getClass().getDeclaredField(fieldName);
		Field field = SimpleBeanUtil.getFieldWithSupper(object.getClass(), fieldName);
		field.setAccessible(true);
		field.set(object, value);
	}

	public static Object getFieldValue(Object object, String fieldName) throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException, NoSuchFieldException, SecurityException {
		// Field field = object.getClass().getDeclaredField(fieldName);
		Field field = SimpleBeanUtil.getFieldWithSupper(object.getClass(), fieldName);
		field.setAccessible(true);
		return field.get(object);
	}
}
