package com.mystore.common.persistence.jdbc;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.mystore.common.persistence.Column;
import com.mystore.common.persistence.jdbc.sql.SQL;

public abstract class ValueObjectJdbcDao<T, SUPID> extends JdbcBaseDao<T> {

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

	public void insert(T object) {
		Collection<Column<T>> columns = table.values();

		String SQL = "INSERT INTO #{table} (#{columnNames}) VALUES(#{columnValues})";
		SQL = sqlSetting(SQL, "table", table.getTableName());
		SQL = sqlSetting(SQL, "columnNames", new InsertIntoContents<T>(columns).toString());
		SQL = sqlSetting(SQL, "columnValues", new ValuesContents<T>(columns).toString());

		jdbcTemplate.update(SQL, providePsSetter(columns, object));
	}

	public void deleteBySupId(SUPID supId) {
		Collection<Column<T>> supIdColumns = getColumnsByFieldName(getSupIdFieldName());
		Collection<Column<T>> pssColumns = new ArrayList<Column<T>>(supIdColumns);

		String SQL = "DELETE FROM #{table} WHERE #{whereContents}";
		SQL = sqlSetting(SQL, "table", table.getTableName());
		SQL = sqlSetting(SQL, "whereContents", getWhereContentsForSupId(supIdColumns));

		T object = produceObjectBySupId(supId);
		jdbcTemplate.update(SQL, providePsSetter(pssColumns, object));
	}

	public List<T> findAllBySupId(SUPID supId) {
		Collection<Column<T>> supIdColumns = getColumnsByFieldName(getSupIdFieldName());
		Collection<Column<T>> sqlColumns = table.values();
		Collection<Column<T>> pssColumns = new ArrayList<Column<T>>(supIdColumns);

		String SQL = "SELECT #{columnNames} FROM #{table} WHERE #{whereContents}";
		SQL = sqlSetting(SQL, "table", table.getTableName());
		SQL = sqlSetting(SQL, "columnNames", new SelectContents<T>(sqlColumns).toString());
		SQL = sqlSetting(SQL, "whereContents", getWhereContentsForSupId(supIdColumns));

		T object = produceObjectBySupId(supId);
		List<T> list = jdbcTemplate.query(SQL, providePsSetter(pssColumns, object), provideRowMapper(sqlColumns));

		return list;
	}

	protected T produceObjectBySupId(SUPID supId) {
		try {
			T object = produceObject();
			Field field = getSupIdField();
			field.setAccessible(true);
			try {
				field.set(object, supId);
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
	protected SUPID fetchSupId(T object) {
		SUPID supId = null;
		try {
			try {
				Field field = getSupIdField();
				field.setAccessible(true);
				supId = (SUPID) field.get(object);
			} catch (SecurityException e) {
				e.printStackTrace();
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return supId;
	}

	protected List<SUPID> fetchSupIdList(List<T> objects) {
		List<SUPID> supIdList = new ArrayList<SUPID>();
		for (T object : objects) {
			supIdList.add(fetchSupId(object));
		}
		return supIdList;
	}

	private String getSupIdFieldName() {
		String supIdFieldName = metaFactory.getValueObjectMeta(clazz.getName()).getSupIdentityObjectFieldName();
		return supIdFieldName;
	}

	private Field getSupIdField() {
		Field supIdField = getField(getSupIdFieldName());
		return supIdField;
	}

	private String getWhereContentsForSupId(Collection<Column<T>> supIdColumns) {
		SQL ql = new SQL();
		String whereContents = null;
		Counter counter = new Counter();
		for (Column<T> column : supIdColumns) {
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

}
