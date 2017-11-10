package com.mystore.common.persistence;

import java.sql.ResultSet;

public interface Rs2ColumnValueSetter<T> {
	public void execute(T object,ResultSet rs) throws Exception ;
}
