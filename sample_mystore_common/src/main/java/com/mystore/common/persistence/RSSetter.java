package com.mystore.common.persistence;

import java.sql.ResultSet;

public interface RSSetter<T> {
	public void execute(T object,ResultSet rs) throws Exception ;
}
