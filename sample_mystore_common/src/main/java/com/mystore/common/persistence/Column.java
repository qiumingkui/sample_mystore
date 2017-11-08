package com.mystore.common.persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Column<T> {
	private String name;

	private PSSetter<T> psSetter;

	private RSSetter<T> rsSetter;

	private boolean isPrimaryKay;

	private boolean isVersion;

	public Column() {
		super();
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPsSetter(
			PSSetter<T> psSetter) {
		this.psSetter = psSetter;
	}

	public void setRsSetter(RSSetter<T> rsSetter) {
		this.rsSetter = rsSetter;
	}

	
	
	public void setPrimaryKay() {
		this.isPrimaryKay = true;
	}

	public void setVersion() {
		this.isVersion = true;
	}

	public void fill(PreparedStatement ps, int index, T object) throws Exception {
		psSetter.execute(ps, index, object);
	}

	public void fill(T object, ResultSet rs) throws Exception   {
		rsSetter.execute(object, rs);
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
