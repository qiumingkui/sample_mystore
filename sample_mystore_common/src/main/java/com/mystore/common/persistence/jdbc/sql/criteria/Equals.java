package com.mystore.common.persistence.jdbc.sql.criteria;

import com.mystore.common.persistence.jdbc.sql.SqlFragment;

public class Equals extends SqlFragment {

	private static final String SQL_WORD = "=";

	public Equals(String left, String right) {
		super();
		this.sql = " " + left + SQL_WORD + right + " ";
	}
}
