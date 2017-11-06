package com.mystore.common.persistence.jdbc.generator;

public class UpdateSqlGenerator implements SqlGenerator {

	private String _sql = "update #{tableName} set #{updateSettings} where #{where}";

	public String gen(String tableName, String updateSettings, String where) {
		String sql = _sql;
		sql = sql.replace("#{tableName}", tableName);
		sql = sql.replace("#{updateSettings}", updateSettings);
		sql = sql.replace("#{where}", where);
		return sql;
	}

	public String gen(String tableName, String[] updateColumnArray, String where) {
		String updateColumns = buildUpdateSettings(updateColumnArray);
		return gen(tableName, updateColumns, where);
	}

	private String buildUpdateSettings(String[] updateColumnArray) {
		String columns = "";
		for (String column : updateColumnArray) {
			columns += (column + ",");
		}
		columns = columns.substring(0, columns.length() - 1);
		return columns;
	}

}
