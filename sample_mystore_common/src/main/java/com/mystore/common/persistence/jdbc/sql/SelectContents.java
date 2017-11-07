package com.mystore.common.persistence.jdbc.sql;

import java.util.Collection;

import com.mystore.common.persistence.Column;

public class SelectContents<T> extends SqlFragment {
	public SelectContents(String sql) {
		super();
		this.sql = sql;
	}

	public SelectContents(Collection<Column<T>> collection) {
		super();

		String columnNames = "";
		for (Column<T> column : collection) {
			columnNames += (column.name() + ",");
		}
		columnNames = columnNames.substring(0, columnNames.length() - 1);
		this.sql = columnNames;
	}

}
