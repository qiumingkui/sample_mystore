package com.mystore.common.persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Column<T> {
	private String name;

	private Column2PsValueSetter<T> psSetter;

	private Rs2ColumnValueSetter<T> rsSetter;

	// private KeyRSSetter keyRsSetter;
	//
	// private KeyPSSetter keyPsSetter;
	//
	private boolean isPrimaryKay;

	private boolean isVersion;

	public Column() {
		super();
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPsSetter(Column2PsValueSetter<T> psSetter) {
		this.psSetter = psSetter;
	}

	public void setRsSetter(Rs2ColumnValueSetter<T> rsSetter) {
		this.rsSetter = rsSetter;
	}

	// public void setKeyPsSetter(KeyPSSetter keyPsSetter) {
	// this.keyPsSetter = keyPsSetter;
	// }
	//
	// public void setKeyRsSetter(KeyRSSetter keyRsSetter) {
	// this.keyRsSetter = keyRsSetter;
	// }

	public void setPrimaryKay() {
		this.isPrimaryKay = true;
	}

	public void setVersion() {
		this.isVersion = true;
	}

	public void fillPs(PreparedStatement ps, int index, T object) throws Exception {
		psSetter.execute(ps, index, object);
	}

	public void fillObj(T object, ResultSet rs) throws Exception {
		rsSetter.execute(object, rs);
	}

	// public void fillKey(Object key, ResultSet rs) throws Exception {
	// keyRsSetter.execute(key, rs);
	// }
	//
	// public void fillPsByKey(PreparedStatement ps, int index, Object key)
	// throws Exception {
	// keyPsSetter.execute(ps, index, key);
	// }

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
