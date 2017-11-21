package com.mystore.common.persistence;

import java.sql.PreparedStatement;

public interface KeyPSSetter {
	public void execute(PreparedStatement ps, int index, Object object) throws Exception;
}
