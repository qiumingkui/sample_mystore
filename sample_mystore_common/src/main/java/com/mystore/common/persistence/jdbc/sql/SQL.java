package com.mystore.common.persistence.jdbc.sql;

import com.mystore.common.persistence.jdbc.sql.condition.And;
import com.mystore.common.persistence.jdbc.sql.condition.Equals;
import com.mystore.common.persistence.jdbc.sql.condition.RoundBracket;

public class SQL {

	// question mark
	public static final String QUESTION_MARK = "?";

	private String sql = "";

	public SQL SELECT(String sql) {
		return attach(new Select(sql).toString());
	}

	public SQL FROM(String sql) {
		return attach(new From(sql).toString());
	}

	public SQL WHERE(String sql) {
		return attach(new Where(sql).toString());
	}

	public SQL UPDATE(String sql) {
		return attach(new Update(sql).toString());
	}

	public SQL SET(String sql) {
		return attach(new Set(sql).toString());
	}

	public SQL INSERT_INTO(String sql) {
		return attach(new InsertInto(sql).toString());
	}

	public SQL VALUES(String sql) {
		return attach(new Values(sql).toString());
	}

	public SQL DELETE_FROM(String sql) {
		return attach(new DeleteFrom(sql).toString());
	}

	public String ROUND_BRACKET(String sql) {

		return new RoundBracket(sql).toString();
	}

	public String RB(String sql) {
		return ROUND_BRACKET(sql);
	}

	public String EQUALS(String left, String right) {
		Equals eq = new Equals(left, right);
		return eq.toString();
	}

	public String AND(String left, String right) {
		And and = new And(left, right);
		return and.toString();
	}

	@Override
	public String toString() {
		return sql.trim();
	}

	protected SQL attach(String sql) {
		this.sql += sql;
		return this;
	}
}
