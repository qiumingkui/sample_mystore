package com.mystore.shop.port.adapter.persistence.repository.jdbc;

import com.mystore.shop.domain.model.category.CategoryTable;

public class ObjRawMapperTest {

	public static void main(String[] args) throws Exception {
		CategoryTable categoryTable = new CategoryTable();
		System.out.println(categoryTable.name());
		System.out.println(categoryTable.get("description").name());
	}

	
}
