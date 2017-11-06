package com.mystore.common.persistence.jdbc.sql;

public class Insert extends SqlFragment {

	private static final String SQL_WORD = "INSERT ";

	public Insert(String sql) {
		super();
		this.sql = SQL_WORD;
		this.sql += sql;
	}
}
