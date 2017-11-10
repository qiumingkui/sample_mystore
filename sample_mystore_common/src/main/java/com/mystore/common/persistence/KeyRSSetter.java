package com.mystore.common.persistence;

import java.sql.ResultSet;

public interface KeyRSSetter {
	public void execute(Object key,ResultSet rs) throws Exception ;
}
