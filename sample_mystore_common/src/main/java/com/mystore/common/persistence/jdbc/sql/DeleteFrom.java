package com.mystore.common.persistence.jdbc.sql;

public class DeleteFrom extends SqlFragment {

	private static final String SQL_WORD = "DELETE FROM ";

	public DeleteFrom(String sql) {
		super();
		this.sql = SQL_WORD;
		this.sql += sql;
	}
}
