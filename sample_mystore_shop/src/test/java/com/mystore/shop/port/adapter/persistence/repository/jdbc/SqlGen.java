package com.mystore.shop.port.adapter.persistence.repository.jdbc;

import com.mystore.common.persistence.Table;

public interface SqlGen<T> {

	public String sql(Table<T> t);
}
