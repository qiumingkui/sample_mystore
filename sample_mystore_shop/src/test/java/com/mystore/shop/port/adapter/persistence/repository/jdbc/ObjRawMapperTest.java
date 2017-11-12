package com.mystore.shop.port.adapter.persistence.repository.jdbc;

import com.mystore.shop.port.adapter.persistence.jdbc.CategoryTable;

public class ObjRawMapperTest {

	public static void main(String[] args) throws Exception {
		CategoryTable categoryTable = new CategoryTable();
		out(categoryTable.name());
		out(categoryTable.column("id").name());
		out(categoryTable.column("name").name());
		out(categoryTable.column("description").name());
		// out(categoryTable.keySet().toString());
	}

	private static void out(String str) {
		System.out.println(str);
	}

}
