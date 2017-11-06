package com.mystore.common.persistence.jdbc.sql;

public class Set extends SqlFragment {

	private static final String SQL_WORD = " SET ";

	public Set(String sql) {
		super();
		this.sql = SQL_WORD;
		this.sql += sql;
	}
}
