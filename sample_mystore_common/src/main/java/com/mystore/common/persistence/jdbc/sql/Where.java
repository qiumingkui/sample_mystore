package com.mystore.common.persistence.jdbc.sql;

public class Where extends SqlFragment {

	private static final String SQL_WORD = " WHERE ";

	public Where(String sql) {
		super();
		this.sql = SQL_WORD;
		this.sql += sql.trim();
	}

}
