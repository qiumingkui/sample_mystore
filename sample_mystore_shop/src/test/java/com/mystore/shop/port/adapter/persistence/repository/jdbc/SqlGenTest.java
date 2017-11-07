package com.mystore.shop.port.adapter.persistence.repository.jdbc;

import com.mystore.common.persistence.Table;
import com.mystore.shop.domain.model.category.Category;
import com.mystore.shop.domain.model.category.CategoryBase;
import com.mystore.shop.domain.model.category.CategoryTable;

public class SqlGenTest {

	public static void main(String[] args) {
		Table<CategoryBase> t = new CategoryTable();
		EntitySqlGenFactory<CategoryBase> genFactory = new EntitySqlGenFactory<CategoryBase>();
		out(genFactory.insertSqlGen().sql(t));
		out(genFactory.updateSqlGen().sql(t));
		out(genFactory.selectSqlGen().sql(t));
		out(genFactory.deleteSqlGen().sql(t));
	}

	private static void out(String string) {
		System.out.println(string);
	}
}
