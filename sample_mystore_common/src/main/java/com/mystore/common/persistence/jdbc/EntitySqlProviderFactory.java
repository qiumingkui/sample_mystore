package com.mystore.common.persistence.jdbc;

import java.util.Collection;
import java.util.ArrayList;

import com.mystore.common.persistence.Column;
import com.mystore.common.persistence.Table;
import com.mystore.common.persistence.jdbc.sql.InsertIntoContents;
import com.mystore.common.persistence.jdbc.sql.SQL;
import com.mystore.common.persistence.jdbc.sql.SelectContents;
import com.mystore.common.persistence.jdbc.sql.SetContents;
import com.mystore.common.persistence.jdbc.sql.ValuesContents;

public class EntitySqlProviderFactory<T> {

	public SqlProviderPair<T> insertSqlProviderPair() {
		SqlProvider<T> sqlProvider = (Table<T> t) -> {
			Collection<Column<T>> collection = new ArrayList<Column<T>>();
			collection.addAll(t.values());

			InsertIntoContents<T> insertIntoContents = new InsertIntoContents<T>(t.getTableName(), collection);
			ValuesContents<T> valuesContents = new ValuesContents<T>(collection);
			String insertSql = new SQL().INSERT_INTO(insertIntoContents.toString()).VALUES(valuesContents.toString())
					.toString();

			return insertSql;
		};

		CollectionProvider<T> collectionProvider = (Table<T> t) -> {
			Collection<Column<T>> insertCollection = new ArrayList<Column<T>>();
			insertCollection.addAll(t.values());
			return insertCollection;
		};

		return new SqlProviderPair<T>(sqlProvider, collectionProvider);
	}

	public SqlProviderPair<T> updateSqlProviderPair() {
		Filter<T> updateSetFilter = (Collection<Column<T>> collection, Column<T> column) -> {
			if (column.isPrimaryKay() || column.isVersion())
				return;
			collection.add(column);
		};

		SqlProvider<T> sqlProvider = (Table<T> t) -> {
			SQL ql = new SQL();
			Collection<Column<T>> updateSetCollection = filt(t.values(), updateSetFilter);
			SetContents<T> setContents = new SetContents<T>(updateSetCollection);

			String updateSql = ql.UPDATE(t.getTableName()).SET(setContents.toString())
					.WHERE(ql.EQUALS(t.getPrimaryKey().getColumnName(), SQL.QUESTION_MARK)).toString();

			return updateSql;
		};

		CollectionProvider<T> collectionProvider = (Table<T> t) -> {
			Collection<Column<T>> updateCollection = new ArrayList<Column<T>>();
			Collection<Column<T>> updateSetCollection = filt(t.values(), updateSetFilter);
			updateCollection.addAll(updateSetCollection);
			updateCollection.add(t.getPrimaryKey());
			return updateCollection;
		};

		return new SqlProviderPair<T>(sqlProvider, collectionProvider);
	}

	public SqlProviderPair<T> deleteSqlProviderPair() {
		SqlProvider<T> sqlProvider = (Table<T> t) -> {
			SQL ql = new SQL();
			String deleteSql = ql.DELETE_FROM(t.getTableName()).WHERE(ql.EQUALS(t.getPrimaryKey().getColumnName(), SQL.QUESTION_MARK))
					.toString();
			return deleteSql;
		};

		CollectionProvider<T> collectionProvider = (Table<T> t) -> {
			Collection<Column<T>> insertCollection = new ArrayList<Column<T>>();
			insertCollection.add(t.getPrimaryKey());
			return insertCollection;
		};
		return new SqlProviderPair<T>(sqlProvider, collectionProvider);
	}

	public SqlProviderPair<T> selectSqlProviderPair() {
		SqlProvider<T> sqlProvider = (Table<T> t) -> {
			SQL ql = new SQL();
			SelectContents<T> selectContents = new SelectContents<T>(t.values());
			String selectSql = ql.SELECT(selectContents.toString()).FROM(t.getTableName())
					.WHERE(ql.EQUALS(t.getPrimaryKey().getColumnName(), SQL.QUESTION_MARK)).toString();
			return selectSql;
		};

		CollectionProvider<T> collectionProvider = (Table<T> t) -> {
			Collection<Column<T>> selectCollection = new ArrayList<Column<T>>();
			selectCollection.addAll(t.values());
			selectCollection.add(t.getPrimaryKey());
			return selectCollection;
		};

		return new SqlProviderPair<T>(sqlProvider, collectionProvider);
	}

	private Collection<Column<T>> filt(Collection<Column<T>> source, Filter<T> filter) {
		Collection<Column<T>> target = new ArrayList<Column<T>>();
		for (Column<T> column : source) {
			filter.doFilt(target, column);
		}
		return target;
	}

	interface Filter<T> {
		void doFilt(Collection<Column<T>> collection, Column<T> column);
	}

}
