package com.mystore.common.persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Column<T> {

	private String name;
	private Column2PsValueSetter<T> psSetter;
	private Rs2ColumnValueSetter<T> rsSetter;
	private boolean isPrimaryKay;
	private boolean isForeignKey;
	private boolean isVersion;

	public Column() {
		super();
	}

	protected void setName(String name) {
		this.name = name;
	}

	protected void setPsSetter(Column2PsValueSetter<T> psSetter) {
		this.psSetter = psSetter;
	}

	protected void setRsSetter(Rs2ColumnValueSetter<T> rsSetter) {
		this.rsSetter = rsSetter;
	}

	public void fillPs(PreparedStatement ps, int index, T object) throws Exception {
		psSetter.execute(ps, index, object);
	}

	public void fillObj(T object, ResultSet rs) throws Exception {
		rsSetter.execute(object, rs);
	}

	public String name() {
		return name;
	}

	protected void setPrimaryKay() {
		this.isPrimaryKay = true;
	}

	protected void setForeignKey() {
		this.isForeignKey = true;
	}

	protected void setVersion() {
		this.isVersion = true;
	}

	public boolean isPrimaryKay() {
		return isPrimaryKay;
	}

	public boolean isForeignKey() {
		return isForeignKey;
	}

	public boolean isVersion() {
		return isVersion;
	}

}
