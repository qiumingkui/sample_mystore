package com.mystore.common.persistence.jdbc.sql;

public class Update extends SqlFragment {

	private static final String SQL_WORD = "UPDATE ";

	public Update(String sql) {
		super();
		this.sql = SQL_WORD;
		this.sql += sql;
	}
}
