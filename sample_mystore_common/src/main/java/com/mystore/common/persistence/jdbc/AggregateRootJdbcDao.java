package com.mystore.common.persistence.jdbc;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.mystore.common.persistence.Column;
import com.mystore.common.persistence.jdbc.sql.SQL;

public abstract class AggregateRootJdbcDao<T, ID> extends JdbcBaseDao<T> {

	@SuppressWarnings("unchecked")
	public AggregateRootJdbcDao() {
		super();
		initClass();
		initMetaFactory();
		if (metaFactory != null && clazz != null) {
			this.table = metaFactory.getTable(clazz.getName());
		} else {
			initTable();
		}
	}

	public void insert(T object) {
		Collection<Column<T>> columns = table.values();

		String SQL = "INSERT INTO #{table} (#{columnNames}) VALUES(#{columnValues})";
		SQL = sqlSetting(SQL, "table", table.getTableName());
		SQL = sqlSetting(SQL, "columnNames", new InsertIntoContents<T>(columns).toString());
		SQL = sqlSetting(SQL, "columnValues", new ValuesContents<T>(columns).toString());

		jdbcTemplate.update(SQL, providePsSetter(columns, object));
	}

	public void deleteById(ID id) {
		String idFieldName = getIdFieldName();
		Collection<Column<T>> idColumns = getColumnsByFieldName(idFieldName);

		String SQL = "DELETE FROM #{table} WHERE #{whereContents}";
		SQL = sqlSetting(SQL, "table", table.getTableName());
		SQL = sqlSetting(SQL, "whereContents", getWhereContentsForId(idColumns));

		jdbcTemplate.update(SQL, providePsSetter(idColumns, produceObject(id)));
	}

	public void update(T object) {
		if (table.getVersion() != null) {
			updateWithVersion(object);
			return;
		}

		Collection<Column<T>> idColumns = getColumnsByFieldName(getIdFieldName());
		Collection<Column<T>> sqlColumns = getUpdateColumns(table.values());
		Collection<Column<T>> pssColumns = new ArrayList<Column<T>>(sqlColumns);
		attachColumns(pssColumns, idColumns);

		String SQL = "UPDATE #{table} SET #{setContents} WHERE #{whereContents}";
		SQL = sqlSetting(SQL, "table", table.getTableName());
		SQL = sqlSetting(SQL, "setContents", new UpdateSetContents<T>(sqlColumns).toString());
		SQL = sqlSetting(SQL, "whereContents", getWhereContentsForId(idColumns));

		jdbcTemplate.update(SQL, providePsSetter(pssColumns, object));
	}

	public void updateWithVersion(T object) {
		Collection<Column<T>> idColumns = getColumnsByFieldName(getIdFieldName());
		Collection<Column<T>> sqlColumns = getUpdateColumns(table.values());
		Collection<Column<T>> pssColumns = new ArrayList<Column<T>>();
		attachColumns(pssColumns, sqlColumns);
		attachColumns(pssColumns, idColumns);
		Column<T> versionColumn = table.getVersion();
		pssColumns.add(versionColumn);

		String SQL = "UPDATE #{table} SET #{setContents},#{version}=#{version}+1 WHERE #{whereContents} AND #{version}=?";
		SQL = sqlSetting(SQL, "table", table.getTableName());
		SQL = sqlSetting(SQL, "setContents", new UpdateSetContents<T>(sqlColumns).toString());
		SQL = sqlSetting(SQL, "version", versionColumn.getColumnName());
		SQL = sqlSetting(SQL, "whereContents", getWhereContentsForId(idColumns));

		jdbcTemplate.update(SQL, providePsSetter(pssColumns, object));
	}

	public T findOneById(ID id) {
		Collection<Column<T>> idColumns = getColumnsByFieldName(getIdFieldName());
		Collection<Column<T>> sqlColumns = table.values();
		Collection<Column<T>> pssColumns = new ArrayList<Column<T>>();
		attachColumns(pssColumns, idColumns);

		String SQL = "SELECT #{columnNames} FROM #{table} WHERE #{whereContents}";
		SQL = sqlSetting(SQL, "table", table.getTableName());
		SQL = sqlSetting(SQL, "columnNames", new SelectContents<T>(sqlColumns).toString());
		SQL = sqlSetting(SQL, "whereContents", getWhereContentsForId(idColumns));

		T object = produceObject(id);

		List<T> list = jdbcTemplate.query(SQL, providePsSetter(pssColumns, object), new ObjectRowMapper<T>(sqlColumns));

		return list.size() > 0 ? (T) (list.get(0)) : null;
	}

	public List<T> findAll() {
		List<ID> ids = findAllId();
		List<T> list = findAll(ids);
		return list;
	}

	public List<T> findAll(List<ID> ids) {
		List<T> list = new ArrayList<T>();
		for (ID id : ids) {
			list.add(findOneById(id));
		}
		return list;
	}

	public List<ID> findAllId() {
		Collection<Column<T>> columns = getColumnsByFieldName(getIdFieldName());

		String SQL = "SELECT #{columnNames} FROM #{table}";
		SQL = sqlSetting(SQL, "table", table.getTableName());
		SQL = sqlSetting(SQL, "columnNames", new SelectContents<T>(columns).toString());

		List<T> list = jdbcTemplate.query(SQL, provideRowMapper(columns));

		List<ID> idList = new ArrayList<ID>();
		for (T object : list) {
			idList.add(fetchId(object));
		}
		return idList;
	}

	protected T produceObject(ID id) {
		try {
			T object = produceObject();
			Field field = getIdField();
			field.setAccessible(true);
			try {
				field.set(object, id);
				return object;
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	protected ID fetchId(T object) {
		ID id = null;
		try {
			try {
				Field field = getIdField();
				field.setAccessible(true);
				id = (ID) field.get(object);
			} catch (SecurityException e) {
				e.printStackTrace();
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return id;
	}

	protected List<ID> fetchIdList(List<T> objects) {
		List<ID> idList = new ArrayList<ID>();
		for (T object : objects) {
			idList.add(fetchId(object));
		}
		return idList;
	}

	private String getIdFieldName() {
		String idFieldName = metaFactory.getAggregateRootMeta(clazz.getName()).getIdentityObjectFieldName();
		return idFieldName;
	}

	private Field getIdField() {
		String idFieldName = getIdFieldName();
		Field idField = getField(idFieldName);
		return idField;
	}

	private String getWhereContentsForId(Collection<Column<T>> idColumns) {
		SQL ql = new SQL();
		String whereContents = null;
		Counter counter = new Counter();
		for (Column<T> column : idColumns) {
			if (counter.current() == 0) {
				whereContents = ql.EQUALS(column.getColumnName(), SQL.QUESTION_MARK);
			}
			if (counter.current() > 0) {
				whereContents = ql.AND(whereContents, ql.EQUALS(column.getColumnName(), SQL.QUESTION_MARK));
			}
			counter.next();
		}
		return whereContents;
	}

	private Collection<Column<T>> getUpdateColumns(Collection<Column<T>> source) {
		Collection<Column<T>> idColumns = getColumnsByFieldName(getIdFieldName());
		Column<T> versionColumn = table.getVersion();

		Collection<Column<T>> columns = filtColumns(source, (Collection<Column<T>> target, Column<T> column) -> {

			for (Column<T> idColumn : idColumns) {
				if (column.getColumnName().equals(idColumn.getColumnName())) {
					return;
				}
			}

			if (versionColumn != null && column.getColumnName().equals(versionColumn.getColumnName())) {
				return;
			}

			target.add(column);
		});
		return columns;
	}

}
