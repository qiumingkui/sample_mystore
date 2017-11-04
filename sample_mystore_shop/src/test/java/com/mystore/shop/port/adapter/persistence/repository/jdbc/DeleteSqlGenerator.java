package com.mystore.shop.port.adapter.persistence.repository.jdbc;

public class DeleteSqlGenerator implements SqlGenerator {

	private String _sql = "delete from #{tableName} where #{where}";

	public String gen(String tableName, String where) {
		String sql = _sql;
		sql=sql.replace("#{tableName}", tableName);
		sql=sql.replace("#{where}", where);
		return sql;
	}

}
