package com.mystore.common.persistence.jdbc.sql;

public class Values extends SqlFragment {

	private static final String SQL_WORD = " VALUES";

	public Values(String sql) {
		super();
		this.sql = SQL_WORD;
		this.sql += sql;
	}
}
