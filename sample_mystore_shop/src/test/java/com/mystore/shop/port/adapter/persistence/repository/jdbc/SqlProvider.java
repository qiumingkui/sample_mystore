package com.mystore.shop.port.adapter.persistence.repository.jdbc;

import com.mystore.common.persistence.Table;

public interface SqlProvider<T> {

	public String provide(Table<T> t);
}
