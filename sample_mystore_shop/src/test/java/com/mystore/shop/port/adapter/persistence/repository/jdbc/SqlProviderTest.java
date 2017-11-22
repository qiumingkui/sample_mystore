package com.mystore.shop.port.adapter.persistence.repository.jdbc;

import com.mystore.common.persistence.Table;
import com.mystore.common.persistence.jdbc.EntitySqlProviderFactory;
import com.mystore.shop.domain.model.category.Category;
import com.mystore.shop.meta.CategoryTable;

public class SqlProviderTest {

	public static void main(String[] args) {
		Table<Category> t = new CategoryTable();
		EntitySqlProviderFactory<Category> genFactory = new EntitySqlProviderFactory<Category>();
		out(genFactory.insertSqlProviderPair().getSqlProvider().provide(t));
		out(genFactory.updateSqlProviderPair().getSqlProvider().provide(t));
		out(genFactory.selectSqlProviderPair().getSqlProvider().provide(t));
		out(genFactory.deleteSqlProviderPair().getSqlProvider().provide(t));
	}

	private static void out(String string) {
		System.out.println(string);
	}
}
