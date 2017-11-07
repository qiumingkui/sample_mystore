package com.mystore.shop.port.adapter.persistence.repository.jdbc;

import com.mystore.common.persistence.jdbc.sql.InsertIntoContents;
import com.mystore.common.persistence.jdbc.sql.SQL;
import com.mystore.common.persistence.jdbc.sql.ValuesContents;
import com.mystore.shop.domain.model.category.Category;
import com.mystore.shop.domain.model.category.CategoryTable;

public class SqlGenTest {

	public static void main(String[] args) {
		CategoryTable t=new CategoryTable() ;
		InsertIntoContents<Category> insertIntoContents = new InsertIntoContents<Category>(t.name(),t.values());
		ValuesContents<Category> valuesContents = new ValuesContents<Category>(t.values());
		String insertSql = new SQL().INSERT_INTO(insertIntoContents.toString()).VALUES(valuesContents.toString()).toString();
		System.out.println(insertSql.toString());
		String selectSql = new SQL().SELECT("id,name").FROM("user").WHERE("id=?").toString();
		System.out.println(selectSql);
		
		String updateSql = new SQL().UPDATE("user").SET("name=?").WHERE("id=?").toString();
		System.out.println(updateSql);

		String deleteSql = new SQL().DELETE_FROM("user").WHERE("id=?").toString();
		System.out.println(deleteSql);

	}
}
