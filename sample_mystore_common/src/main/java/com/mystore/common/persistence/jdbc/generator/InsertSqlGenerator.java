package com.mystore.common.persistence.jdbc.generator;

public class InsertSqlGenerator implements SqlGenerator {

	private String _sql = "insert into #{tableName}(#{insertColumns}) values(#{inserValues})";

	public String gen(String tableName, String insertColumns, String inserValues) {
		String sql = _sql;
		sql = sql.replace("#{tableName}", tableName);
		sql = sql.replace("#{insertColumns}", insertColumns);
		sql = sql.replace("#{inserValues}", inserValues);

		return sql;
	}

	public String gen(String tableName, String[] columnArray) {

		String insertColumns = buildInsertColumns(columnArray);

		String inserValues = buildInserValues(columnArray);

		return gen(tableName, insertColumns, inserValues);
	}

	private String buildInsertColumns(String[] columnArray) {
		String columns = "";
		for (String column : columnArray) {
			columns += (column + ",");
		}
		columns = columns.substring(0, columns.length() - 1);
		return columns;
	}

	@SuppressWarnings("unused")
	private String buildInserValues(String[] columnArray) {
		String values = "";
		for (String column : columnArray) {
			values += "?,";
		}
		values = values.substring(0, values.length() - 1);
		return values;
	}

}
