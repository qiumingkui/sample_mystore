package com.mystore.common.persistence;

import java.sql.ResultSet;

public interface ResultSetSetter<T> {
	public void execute(T object,ResultSet rs) throws Exception;
}
