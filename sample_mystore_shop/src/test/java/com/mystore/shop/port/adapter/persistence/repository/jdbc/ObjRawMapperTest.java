package com.mystore.shop.port.adapter.persistence.repository.jdbc;

import com.mystore.shop.meta.CategoryTable;

public class ObjRawMapperTest {

	public static void main(String[] args) throws Exception {
		CategoryTable categoryTable = new CategoryTable();
		out(categoryTable.getTableName());
		out(categoryTable.column("id").getColumnName());
		out(categoryTable.column("name").getColumnName());
		out(categoryTable.column("description").getColumnName());
		// out(categoryTable.keySet().toString());
	}

	private static void out(String str) {
		System.out.println(str);
	}

}
