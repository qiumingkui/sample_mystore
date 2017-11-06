package com.mystore.common.persistence.jdbc.sql;

public class Into extends SqlFragment {

	private static final String SQL_WORD = " INTO ";

	public Into(String sql) {
		super();
		this.sql = SQL_WORD;
		this.sql += sql;
	}
}
