package com.mystore.common.persistence.jdbc.sql;

import java.util.Collection;
import java.util.Iterator;

import com.mystore.common.persistence.Column;

public class ValuesContents<T> extends SqlFragment {
	public ValuesContents(String sql) {
		super();
		this.sql = sql;
	}

	public ValuesContents(Collection<Column<T>> collection) {
		super();

		String valuesString = "";
		Iterator<Column<T>> iterator = collection.iterator();
		while(iterator.hasNext()) {
			valuesString += ("?,");
			iterator.next();
		}
		valuesString = valuesString.substring(0, valuesString.length() - 1);
		this.sql = "(" + valuesString + ")";
	}

}
