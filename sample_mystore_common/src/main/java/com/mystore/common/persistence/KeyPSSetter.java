package com.mystore.common.persistence;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface KeyPSSetter {
	public void execute(PreparedStatement ps, int index, Object object) throws Exception;
}
