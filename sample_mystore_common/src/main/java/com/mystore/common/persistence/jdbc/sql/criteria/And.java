package com.mystore.common.persistence.jdbc.sql.criteria;

import com.mystore.common.persistence.jdbc.sql.SqlFragment;

public class And extends SqlFragment {

	private static final String SQL_WORD = " AND ";

	public And(String left, String right) {
		super();
		this.sql = " " + left + SQL_WORD + right + " ";
	}
}
