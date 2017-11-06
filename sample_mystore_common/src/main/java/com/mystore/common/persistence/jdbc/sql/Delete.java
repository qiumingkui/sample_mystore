package com.mystore.common.persistence.jdbc.sql;

public class Delete extends SqlFragment {

	private static final String SQL_WORD = "DELETE ";

	public Delete(String sql) {
		super();
		this.sql = SQL_WORD;
		this.sql += sql;
	}
}
