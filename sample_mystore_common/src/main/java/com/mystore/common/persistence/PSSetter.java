package com.mystore.common.persistence;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface PSSetter<T> {
	public void execute(PreparedStatement ps, int index, T object) throws SQLException;
}
