package com.mystore.common.persistence.jdbc.sql;

import java.util.Collection;

import com.mystore.common.persistence.Column;

public class InsertIntoContents<T> extends SqlFragment {
	public InsertIntoContents(String sql) {
		super();
		this.sql = sql;
	}

	public InsertIntoContents(String tableName, Collection<Column<T>> collection) {
		super();

		String columnNames = "";
		for (Column<T> column : collection) {
			columnNames += (column.name() + ",");
		}
		columnNames = columnNames.substring(0, columnNames.length() - 1);
		this.sql = tableName + "(" + columnNames + ")";
	}

}
