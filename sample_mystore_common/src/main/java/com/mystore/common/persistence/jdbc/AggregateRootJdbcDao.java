package com.mystore.common.persistence.jdbc;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.mystore.common.meta.ClassMeta;
import com.mystore.common.meta.domain.AggregateRootMeta;
import com.mystore.common.persistence.Column;
import com.mystore.common.utils.SimpleBeanUtil;

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
		Collection<Column<T>> columns = table.columns();

		String SQL = "INSERT INTO #{table} (#{columnNames}) VALUES(#{columnValues})";
		SQL = sqlSetting(SQL, "table", table.getTableName());
		SQL = sqlSetting(SQL, "columnNames", new InsertIntoContents<T>(columns).toString());
		SQL = sqlSetting(SQL, "columnValues", new ValuesContents<T>(columns).toString());

		jdbcTemplate.update(SQL, providePsSetter(columns, object));
	}

	public T findOne(T object) {
		Collection<Column<T>> sqlColumns = table.columns();
		Collection<Column<T>> pssColumns = new ArrayList<Column<T>>();
		pssColumns.add(table.primaryKey());

		String SQL = "SELECT #{columnNames} FROM #{table} WHERE #{pk}=?";
		SQL = sqlSetting(SQL, "table", table.getTableName());
		SQL = sqlSetting(SQL, "columnNames", new SelectContents<T>(sqlColumns).toString());
		SQL = sqlSetting(SQL, "pk", table.primaryKey().getColumnName());

		List<T> list = jdbcTemplate.query(SQL, providePsSetter(pssColumns, object), new ObjectRowMapper<T>(sqlColumns));

		return list.size() > 0 ? (T) (list.get(0)) : null;
	}

	public T findOneById(ID id) {
		T object = produceObject(id);
		return findOne(object);
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
		Collection<Column<T>> columns = new ArrayList<Column<T>>();
		columns.add(table.primaryKey());

		String SQL = "SELECT #{pk} FROM #{table}";
		SQL = sqlSetting(SQL, "table", table.getTableName());
		SQL = sqlSetting(SQL, "pk", table.primaryKey().getColumnName());

		List<T> list = jdbcTemplate.query(SQL, provideRowMapper(columns));

		List<ID> idList = new ArrayList<ID>();
		for (T object : list) {
			idList.add(fetchId(object));
		}
		return idList;
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
		SQL = sqlSetting(SQL, "table", table.getTableName());
		SQL = sqlSetting(SQL, "setContents", new UpdateSetContents<T>(sqlColumns).toString());
		SQL = sqlSetting(SQL, "pk", table.primaryKey().getColumnName());

		jdbcTemplate.update(SQL, providePsSetter(pssColumns, object));
	}

	public void delete(T object) {
		Collection<Column<T>> pssColumns = new ArrayList<Column<T>>();
		pssColumns.add(table.primaryKey());

		String SQL = "DELETE FROM #{table} WHERE #{pk}=?";
		SQL = sqlSetting(SQL, "table", table.getTableName());
		SQL = sqlSetting(SQL, "pk", table.primaryKey().getColumnName());

		jdbcTemplate.update(SQL, providePsSetter(pssColumns, object));
	}

	public void deleteById(ID id) {
		T object = produceObject(id);
		delete(object);
	}

	// abstract protected T produceObject(ID id);

	protected T produceObject(ID id) {
		try {
			T object = produceObject();
			// Field field =
			// SimpleBeanUtil.getFieldWithSupper(object.getClass(),
			// table.getIdClassName());
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

	// abstract protected ID fetchID(T object);

	@SuppressWarnings("unchecked")
	protected ID fetchId(T object) {
		ID id = null;
		try {
			try {
				// Field field =
				// SimpleBeanUtil.getFieldWithSupper(object.getClass(),
				// table.getIdClassName());
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

	private Field getIdField() {
		String className = clazz.getName();
		String idFieldName = metaFactory.getAggregateRootMeta(className).getIdentityObjectFieldName();
		Field idField = metaFactory.getClassMeta(className).getField(idFieldName);
		return idField;

	}
}