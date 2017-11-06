package com.mystore.common.persistence.jdbc.sql;

public class InsertInto extends SqlFragment {

	private static final String SQL_WORD = "INSERT INTO ";

	public InsertInto(String sql) {
		super();
		this.sql = SQL_WORD;
		this.sql += sql;
	}
}
