package com.mystore.common.persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

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

}
