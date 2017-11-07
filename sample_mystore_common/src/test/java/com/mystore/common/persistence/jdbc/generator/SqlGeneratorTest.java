package com.mystore.common.persistence.jdbc.generator;

public class SqlGeneratorTest {

	public static void main(String[] args) {

		String tableName ="user";
		String where = "userId=?";
		String[] columns = new String[] { "userId", "userName" };
		InsertSqlGenerator insertSqlGenerator = new InsertSqlGenerator();
		out(insertSqlGenerator.gen(tableName, columns));
		SelectSqlGenerator selectSqlGenerator = new SelectSqlGenerator();
		out(selectSqlGenerator.gen(tableName, columns, where));
		UpdateSqlGenerator updateSqlGenerator = new UpdateSqlGenerator();
		out(updateSqlGenerator.gen(tableName, columns, where));
		DeleteSqlGenerator deleteSqlGenerator = new DeleteSqlGenerator();
		out(deleteSqlGenerator.gen(tableName, where));
	}

	public static void out(String outMsg) {
		System.out.println(outMsg);
	}
}
