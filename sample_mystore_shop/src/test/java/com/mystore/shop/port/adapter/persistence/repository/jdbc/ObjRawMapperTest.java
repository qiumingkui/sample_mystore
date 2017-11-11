package com.mystore.shop.port.adapter.persistence.repository.jdbc;

import com.mystore.shop.port.adapter.persistence.jdbc.CategoryTable;

public class ObjRawMapperTest {

	public static void main(String[] args) throws Exception {
		CategoryTable categoryTable = new CategoryTable();
		out(categoryTable.name());
		out(categoryTable.get("id").name());
		out(categoryTable.get("name").name());
		out(categoryTable.get("description").name());
		out(categoryTable.keySet().toString());
	}

	private static void out(String str){
		System.out.println(str);
	}
	
}
