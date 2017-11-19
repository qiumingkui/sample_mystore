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
			collection.addAll(t.columns());

			InsertIntoContents<T> insertIntoContents = new InsertIntoContents<T>(t.name(), collection);
			ValuesContents<T> valuesContents = new ValuesContents<T>(collection);
			String insertSql = new SQL().INSERT_INTO(insertIntoContents.toString()).VALUES(valuesContents.toString())
					.toString();

			return insertSql;
		};

		CollectionProvider<T> collectionProvider = (Table<T> t) -> {
			Collection<Column<T>> insertCollection = new ArrayList<Column<T>>();
			insertCollection.addAll(t.columns());
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
			Collection<Column<T>> updateSetCollection = filt(t.columns(), updateSetFilter);
			SetContents<T> setContents = new SetContents<T>(updateSetCollection);

			String updateSql = ql.UPDATE(t.name()).SET(setContents.toString())
					.WHERE(ql.EQUALS(t.primaryKey().getColumnName(), SQL.QUESTION_MARK)).toString();

			return updateSql;
		};

		CollectionProvider<T> collectionProvider = (Table<T> t) -> {
			Collection<Column<T>> updateCollection = new ArrayList<Column<T>>();
			Collection<Column<T>> updateSetCollection = filt(t.columns(), updateSetFilter);
			updateCollection.addAll(updateSetCollection);
			updateCollection.add(t.primaryKey());
			return updateCollection;
		};

		return new SqlProviderPair<T>(sqlProvider, collectionProvider);
	}

	public SqlProviderPair<T> deleteSqlProviderPair() {
		SqlProvider<T> sqlProvider = (Table<T> t) -> {
			SQL ql = new SQL();
			String deleteSql = ql.DELETE_FROM(t.name()).WHERE(ql.EQUALS(t.primaryKey().getColumnName(), SQL.QUESTION_MARK))
					.toString();
			return deleteSql;
		};

		CollectionProvider<T> collectionProvider = (Table<T> t) -> {
			Collection<Column<T>> insertCollection = new ArrayList<Column<T>>();
			insertCollection.add(t.primaryKey());
			return insertCollection;
		};
		return new SqlProviderPair<T>(sqlProvider, collectionProvider);
	}

	public SqlProviderPair<T> selectSqlProviderPair() {
		SqlProvider<T> sqlProvider = (Table<T> t) -> {
			SQL ql = new SQL();
			SelectContents<T> selectContents = new SelectContents<T>(t.columns());
			String selectSql = ql.SELECT(selectContents.toString()).FROM(t.name())
					.WHERE(ql.EQUALS(t.primaryKey().getColumnName(), SQL.QUESTION_MARK)).toString();
			return selectSql;
		};

		CollectionProvider<T> collectionProvider = (Table<T> t) -> {
			Collection<Column<T>> selectCollection = new ArrayList<Column<T>>();
			selectCollection.addAll(t.columns());
			selectCollection.add(t.primaryKey());
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
