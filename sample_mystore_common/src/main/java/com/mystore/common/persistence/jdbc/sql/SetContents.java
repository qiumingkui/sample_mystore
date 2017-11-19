package com.mystore.common.persistence.jdbc.sql;

import java.util.Collection;

import com.mystore.common.persistence.Column;

public class SetContents<T> extends SqlFragment {
	public SetContents(String sql) {
		super();
		this.sql = sql;
	}

	public SetContents(Collection<Column<T>> collection) {
		super();

		String setString = "";
		for (Column<T> column : collection) {
			setString += (column.getColumnName() + "=?,");
		}
		setString = setString.substring(0, setString.length() - 1);
		this.sql = setString;
	}

}
