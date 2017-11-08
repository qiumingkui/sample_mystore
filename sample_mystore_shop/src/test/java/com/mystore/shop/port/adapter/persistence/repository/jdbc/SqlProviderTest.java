package com.mystore.shop.port.adapter.persistence.repository.jdbc;

import com.mystore.common.persistence.Table;
import com.mystore.shop.domain.model.category.Category;
import com.mystore.shop.domain.model.category.CategoryBase;
import com.mystore.shop.domain.model.category.CategoryTable;

public class SqlProviderTest {

	public static void main(String[] args) {
		Table<CategoryBase> t = new CategoryTable();
		EntitySqlProviderFactory<CategoryBase> genFactory = new EntitySqlProviderFactory<CategoryBase>();
		out(genFactory.insertSqlProviderPair().getSqlProvider().provide(t));
		out(genFactory.updateSqlProviderPair().getSqlProvider().provide(t));
		out(genFactory.selectSqlProviderPair().getSqlProvider().provide(t));
		out(genFactory.deleteSqlProviderPair().getSqlProvider().provide(t));
	}

	private static void out(String string) {
		System.out.println(string);
	}
}
