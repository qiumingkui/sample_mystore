package com.mystore.common.persistence.jdbc.generator;

public class SelectSqlGenerator implements SqlGenerator {

	private final String _sql = "select #{selectColumns} from #{tableName} where #{where}";

	public String gen(String tableName, String selectColumns, String where) {
		String sql=_sql;
		sql=sql.replace("#{tableName}", tableName);
		sql=sql.replace("#{selectColumns}", selectColumns);
		sql=sql.replace("#{where}", where);
		return sql;
	}
	
	public String gen(String tableName, String[] selectColumnArray, String where) {
		String selectColumns = buildSelectColumns(selectColumnArray);
		return gen(tableName,selectColumns,where);
	}

	private String buildSelectColumns(String[] columnArray) {
		String columns = "";
		for (String column : columnArray) {
			columns += (column + ",");
		}
		columns = columns.substring(0, columns.length() - 1);
		return columns;
	}

}
