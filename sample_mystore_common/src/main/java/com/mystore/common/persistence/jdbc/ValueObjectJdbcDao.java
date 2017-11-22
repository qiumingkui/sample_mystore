package com.mystore.common.persistence.jdbc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.mystore.common.persistence.Column;

public abstract class ValueObjectJdbcDao<T, FK> extends JdbcBaseDao<T> {

	public ValueObjectJdbcDao() {
		super();
		initClass();
		initMetaFactory();
		if (metaFactory != null && clazz != null) {
			this.table = metaFactory.getTable(clazz.getName());
		} else {
			initTable();
		}
	}

	public List<T> findAll(T object) {
		Collection<Column<T>> sqlColumns = table.columns();
		Collection<Column<T>> pssColumns = new ArrayList<Column<T>>();
		pssColumns.add(table.foreignKey());

		String SQL = "SELECT #{columnNames} FROM #{table} WHERE #{fk}=?";
		SQL = sqlSetting(SQL, "table", table.getTableName());
		SQL = sqlSetting(SQL, "columnNames", new SelectContents<T>(sqlColumns).toString());
		SQL = sqlSetting(SQL, "fk", table.foreignKey().getColumnName());

		List<T> list = jdbcTemplate.query(SQL, providePsSetter(pssColumns, object), provideRowMapper(sqlColumns));

		return list;
	}

	public List<T> findAllByFK(FK fk) {

		T object = produceObject(fk);
		List<T> list = findAll(object);

		return list;
	}

	public void insert(T object) {
		Collection<Column<T>> columns = table.columns();

		String SQL = "INSERT INTO #{table} (#{columnNames}) VALUES(#{columnValues})";
		SQL = sqlSetting(SQL, "table", table.getTableName());
		SQL = sqlSetting(SQL, "columnNames", new InsertIntoContents<T>(columns).toString());
		SQL = sqlSetting(SQL, "columnValues", new ValuesContents<T>(columns).toString());

		jdbcTemplate.update(SQL, providePsSetter(columns, object));
	}

	public void delete(T object) {
		Collection<Column<T>> pssColumns = new ArrayList<Column<T>>();
		pssColumns.add(table.foreignKey());

		String SQL = "DELETE FROM #{table} WHERE #{fk}=?";
		SQL = sqlSetting(SQL, "table", table.getTableName());
		SQL = sqlSetting(SQL, "fk", table.foreignKey().getColumnName());

		jdbcTemplate.update(SQL, providePsSetter(pssColumns, object));
	}

	public void deleteByFK(FK fk) {
		T object = produceObject(fk);
		delete(object);
	}

	abstract protected T produceObject(FK fk);

}
