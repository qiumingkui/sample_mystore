package com.mystore.common.persistence.jdbc.sql.criteria;

import com.mystore.common.persistence.jdbc.sql.SqlFragment;

public class RoundBracket extends SqlFragment {
	private static final String LEFT = "(";
	private static final String RIGHT = ")";

	public RoundBracket(String sql) {
		super();
		this.sql = LEFT+sql.trim()+RIGHT;
	}

}
