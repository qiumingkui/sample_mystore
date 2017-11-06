package com.mystore.common.persistence.jdbc.sql;

public class Select extends SqlFragment {

	private static final String SQL_WORD = "SELECT ";

	public Select(String sql) {
		super();
		this.sql = SQL_WORD;
		this.sql += sql;
	}
}
