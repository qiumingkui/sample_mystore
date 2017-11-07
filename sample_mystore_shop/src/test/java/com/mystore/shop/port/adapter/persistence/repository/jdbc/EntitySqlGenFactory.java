package com.mystore.shop.port.adapter.persistence.repository.jdbc;

import com.mystore.common.persistence.Table;
import com.mystore.common.persistence.jdbc.sql.InsertIntoContents;
import com.mystore.common.persistence.jdbc.sql.SQL;
import com.mystore.common.persistence.jdbc.sql.SelectContents;
import com.mystore.common.persistence.jdbc.sql.SetContents;
import com.mystore.common.persistence.jdbc.sql.ValuesContents;

public class EntitySqlGenFactory<T> {

	public SqlGen<T> insertSqlGen() {
		SqlGen<T> sqlGen = (Table<T> t) -> {
			InsertIntoContents<T> insertIntoContents = new InsertIntoContents<T>(t.name(), t.values());
			ValuesContents<T> valuesContents = new ValuesContents<T>(t.values());
			String insertSql = new SQL().INSERT_INTO(insertIntoContents.toString()).VALUES(valuesContents.toString())
					.toString();
			return insertSql;
		};
		return sqlGen;
	}

	public SqlGen<T> deleteSqlGen() {
		SQL ql = new SQL();
		SqlGen<T> sqlGen = (Table<T> t) -> {
			String deleteSql = new SQL().DELETE_FROM(t.name())
					.WHERE(ql.EQUALS(t.primaryKey().name(), SQL.QUESTION_MARK)).toString();
			return deleteSql;
		};
		return sqlGen;
	}

	public SqlGen<T> updateSqlGen() {
		SQL ql = new SQL();
		SqlGen<T> sqlGen = (Table<T> t) -> {
			SetContents<T> setContents = new SetContents<T>(t.values());
			String updateSql = new SQL().UPDATE(t.name()).SET(setContents.toString())
					.WHERE(ql.EQUALS(t.primaryKey().name(), SQL.QUESTION_MARK)).toString();
			return updateSql;
		};
		return sqlGen;
	}

	public SqlGen<T> selectSqlGen() {
		SQL ql = new SQL();
		SqlGen<T> sqlGen = (Table<T> t) -> {
			SelectContents<T> selectContents = new SelectContents<T>(t.values());
			String selectSql = new SQL().SELECT(selectContents.toString()).FROM(t.name())
					.WHERE(ql.EQUALS(t.primaryKey().name(), SQL.QUESTION_MARK)).toString();
			return selectSql;
		};
		return sqlGen;
	}

}
