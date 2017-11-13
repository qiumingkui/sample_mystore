package com.mystore.common.persistence.jdbc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.mystore.common.persistence.Column;

public abstract class JdbcEntityDao<T, K> extends JdbcBaseDao<T> {

	public JdbcEntityDao() {
		super();
		init();
	}

	public void insert(T object) {
		Collection<Column<T>> columns = table.columns();

		String SQL = "INSERT INTO #{table} (#{columnNames}) VALUES(#{columnValues})";
		SQL = replaceSql(SQL, "table", table.name());
		SQL = replaceSql(SQL, "columnNames", new InsertIntoContents<T>(columns).toString());
		SQL = replaceSql(SQL, "columnValues", new ValuesContents<T>(columns).toString());

		jdbcTemplate.update(SQL, providePsSetter(columns, object));
	}

	public T findOne(T object) {
		Collection<Column<T>> sqlColumns = table.columns();
		Collection<Column<T>> pssColumns = new ArrayList<Column<T>>();
		pssColumns.add(table.primaryKey());

		String SQL = "SELECT #{columnNames} FROM #{table} WHERE #{pk}=?";
		SQL = replaceSql(SQL, "table", table.name());
		SQL = replaceSql(SQL, "columnNames", new SelectContents<T>(sqlColumns).toString());
		SQL = replaceSql(SQL, "pk", table.primaryKey().name());

		List<T> list = jdbcTemplate.query(SQL, providePsSetter(pssColumns, object), new ObjectRowMapper<T>(sqlColumns));

		return list.size() > 0 ? (T) (list.get(0)) : null;
	}

	public T findOneById(K key) {
		T object = produceObject(key);
		return findOne(object);
	}

	public List<T> findAll() {
		List<K> keys = findAllKey();
		List<T> list = findAll(keys);
		return list;
	}

	// public List<T> findAll() {
	// Collection<Column<T>> columns = new ArrayList<Column<T>>();
	// columns.add(table.primaryKey());
	//
	// String SQL = "SELECT #{pk} FROM #{table}";
	// SQL = replaceSql(SQL, "table", table.name());
	// SQL = replaceSql(SQL, "pk", table.primaryKey().name());
	//
	// List<T> objectWithKeyList = jdbcTemplate.query(SQL,
	// provideRowMapper(columns));
	//
	// List<T> list = new ArrayList<T>();
	// for (T objectWithKey : objectWithKeyList) {
	// list.add(findOneById(fetchKey(objectWithKey)));
	// }
	// return list;
	// }

	public List<T> findAll(List<K> keys) {
		List<T> list = new ArrayList<T>();
		for (K key : keys) {
			list.add(findOneById(key));
		}
		return list;
	}

	public List<K> findAllKey() {
		Collection<Column<T>> columns = new ArrayList<Column<T>>();
		columns.add(table.primaryKey());

		String SQL = "SELECT #{pk} FROM #{table}";
		SQL = replaceSql(SQL, "table", table.name());
		SQL = replaceSql(SQL, "pk", table.primaryKey().name());

		List<T> list = jdbcTemplate.query(SQL, provideRowMapper(columns));

		List<K> keyList = new ArrayList<K>();
		for (T object : list) {
			keyList.add(fetchKey(object));
		}
		return keyList;
	}

	public void update(T object) {
		Collection<Column<T>> sqlColumns = filtColumns(table.columns(),
				(Collection<Column<T>> target, Column<T> column) -> {
					if ((!column.isPrimaryKay()) && (!column.isVersion()))
						target.add(column);
				});

		Collection<Column<T>> pssColumns = new ArrayList<Column<T>>(sqlColumns);
		pssColumns.add(table.primaryKey());

		String SQL = "UPDATE #{table} SET #{setContents} WHERE #{pk}=?";
		SQL = replaceSql(SQL, "table", table.name());
		SQL = replaceSql(SQL, "setContents", new UpdateSetContents<T>(sqlColumns).toString());
		SQL = replaceSql(SQL, "pk", table.primaryKey().name());

		jdbcTemplate.update(SQL, providePsSetter(pssColumns, object));
	}

	public void delete(T object) {
		Collection<Column<T>> pssColumns = new ArrayList<Column<T>>();
		pssColumns.add(table.primaryKey());

		String SQL = "DELETE FROM #{table} WHERE #{pk}=?";
		SQL = replaceSql(SQL, "table", table.name());
		SQL = replaceSql(SQL, "pk", table.primaryKey().name());

		jdbcTemplate.update(SQL, providePsSetter(pssColumns, object));
	}

	public void deleteById(K key) {
		T object = produceObject(key);
		delete(object);
	}

	abstract protected T produceObject(K key);

	abstract protected K fetchKey(T object);

	protected List<K> fetchKeyList(List<T> objects) {
		List<K> keys = new ArrayList<K>();
		for (T object : objects) {
			keys.add(fetchKey(object));
		}
		return keys;
	}

}
