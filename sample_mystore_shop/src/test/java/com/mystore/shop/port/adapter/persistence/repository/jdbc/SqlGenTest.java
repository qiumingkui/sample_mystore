package com.mystore.shop.port.adapter.persistence.repository.jdbc;

import com.mystore.common.persistence.Table;
import com.mystore.shop.domain.model.category.Category;
import com.mystore.shop.domain.model.category.CategoryTable;

public class SqlGenTest {

	public static void main(String[] args) {
		Table<Category> t = new CategoryTable();
		EntitySqlGenFactory<Category> genFactory = new EntitySqlGenFactory<Category>();
		out(genFactory.insertSqlGen().sql(t));
		out(genFactory.updateSqlGen().sql(t));
		out(genFactory.selectSqlGen().sql(t));
		out(genFactory.deleteSqlGen().sql(t));
	}

	private static void out(String string) {
		System.out.println(string);
	}
}
