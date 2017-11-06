package com.mystore.common.persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Column<T> {
	private String name;

	private PreparedStatementParameteSetter<T> preparedStatementParameteSetter;

	private ResultSetSetter<T> resultSetSetter;

	private boolean isPrimaryKay;

	private boolean isVersion;

	public Column() {
		super();
	}

	public void changeName(String name) {
		this.name = name;
	}

	public void changePreparedStatementParameteSetter(
			PreparedStatementParameteSetter<T> preparedStatementParameteSetter) {
		this.preparedStatementParameteSetter = preparedStatementParameteSetter;
	}

	public void changeResultSetSetter(ResultSetSetter<T> resultSetSetter) {
		this.resultSetSetter = resultSetSetter;
	}

	
	
	public void setPrimaryKay() {
		this.isPrimaryKay = true;
	}

	public void setVersion() {
		this.isVersion = true;
	}

	public void assign(PreparedStatement ps, int index, T object) throws Exception {
		preparedStatementParameteSetter.execute(ps, index, object);
	}

	public void assign(T object, ResultSet rs) throws Exception {
		resultSetSetter.execute(object, rs);
	}

	public String name() {
		return name;
	}

	public boolean isPrimaryKay() {
		return isPrimaryKay;
	}

	public boolean isVersion() {
		return isVersion;
	}

}
