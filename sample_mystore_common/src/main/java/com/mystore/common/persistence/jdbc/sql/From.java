package com.mystore.common.persistence.jdbc.sql;

public class From extends SqlFragment {

	private static final String SQL_WORD = " FROM ";

	public From(String sql) {
		super();
		this.sql = SQL_WORD;
		this.sql += sql;
	}

}
