package com.mystore.common.persistence;

import java.sql.PreparedStatement;

public interface Column2PsValueSetter<T> {
	public void execute(PreparedStatement ps, int index, T object) throws Exception;
}
