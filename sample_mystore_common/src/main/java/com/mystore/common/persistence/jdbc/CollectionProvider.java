package com.mystore.common.persistence.jdbc;

import java.util.Collection;

import com.mystore.common.persistence.Column;
import com.mystore.common.persistence.Table;

public interface CollectionProvider<T> {
	public Collection<Column<T>> provide(Table<T> t);
}
