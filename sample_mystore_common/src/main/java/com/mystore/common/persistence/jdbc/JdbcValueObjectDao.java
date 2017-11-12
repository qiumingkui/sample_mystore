package com.mystore.common.persistence.jdbc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.mystore.common.persistence.Column;

public abstract class JdbcValueObjectDao<T, FK> extends JdbcBaseDao<T>{

	public JdbcValueObjectDao() {
		super();
		init();
	}

	public List<T> findAllByFK(FK fk) {
		Collection<Column<T>> sqlColumns = table.columns();
		Collection<Column<T>> pssColumns = new ArrayList<Column<T>>();
		pssColumns.add(table.primaryKey());

		String SQL = "SELECT #{columnNames} FROM #{table} WHERE #{pk}=?";
		SQL = replaceSql(SQL, "table", table.name());
		SQL = replaceSql(SQL, "columnNames", new SelectContents<T>(sqlColumns).toString());
		SQL = replaceSql(SQL, "pk", table.primaryKey().name());

		List<T> list = jdbcTemplate.query(SQL, new Object[]{fk}, new ObjectRowMapper<T>(sqlColumns));

		return list;
	}

	
	public void insert(T object) {
		Collection<Column<T>> columns = table.columns();

		String SQL = "INSERT INTO #{table} (#{columnNames}) VALUES(#{columnValues})";
		SQL = replaceSql(SQL, "table", table.name());
		SQL = replaceSql(SQL, "columnNames", new InsertIntoContents<T>(columns).toString());
		SQL = replaceSql(SQL, "columnValues", new ValuesContents<T>(columns).toString());

		jdbcTemplate.update(SQL, providePsSetter(columns, object));
	}

	
	
	public void deleteByEntityId(FK fk) {
		Collection<Column<T>> pssColumns = new ArrayList<Column<T>>();
		pssColumns.add(table.primaryKey());

		String SQL = "DELETE FROM #{table} WHERE #{fk}=?";
		SQL = replaceSql(SQL, "table", table.name());
		SQL = replaceSql(SQL, "fk", table.primaryKey().name());

		jdbcTemplate.update(SQL, new Object[]{fk});
	}


}
