package com.mystore.common.persistence.jdbc.sql;

public class SQLTest {

	public static void main(String[] args) {
		String selectSql = new SQL().SELECT("id,name").FROM("user").WHERE("id=?").toString();
		System.out.println(selectSql);

		String updateSql = new SQL().UPDATE("user").SET("name=?").WHERE("id=?").toString();
		System.out.println(updateSql);
		
		String insertSql = new SQL().INSERT("user").INTO("id,name").VALUES("?,?").toString();
		System.out.println(insertSql);
		
		String deleteSql = new SQL().DELETE("user").WHERE("id=?").toString();
		System.out.println(deleteSql);
	}

}
