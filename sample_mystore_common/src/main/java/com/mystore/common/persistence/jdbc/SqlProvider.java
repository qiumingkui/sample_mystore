package com.mystore.common.persistence.jdbc;

import com.mystore.common.persistence.Table;

public interface SqlProvider<T> {

	public String provide(Table<T> t);
}
