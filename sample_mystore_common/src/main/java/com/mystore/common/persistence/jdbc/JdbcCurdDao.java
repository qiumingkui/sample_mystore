package com.mystore.common.persistence.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import com.mystore.common.persistence.Column;
import com.mystore.common.persistence.Table;
import com.mystore.common.persistence.jdbc.sql.SqlFragment;

public abstract class JdbcCurdDao<T, K> {

	protected Table<T> table;

	protected JdbcTemplate jdbcTemplate;

	public JdbcCurdDao() {
		super();
		init();
	}

	public void insert(T object) {
		Collection<Column<T>> columns = table.values();

		String SQL = "INSERT INTO #{table} (#{columnNames}) VALUES(#{columnValues})";
		SQL = replaceSql(SQL, "table", table.name());
		SQL = replaceSql(SQL, "columnNames", new InsertIntoContents<T>(columns).toString());
		SQL = replaceSql(SQL, "columnValues", new ValuesContents<T>(columns).toString());

		jdbcTemplate.update(SQL, providePsSetter(columns, object));
	}

	public T find(T object) {
		Collection<Column<T>> sqlColumns = table.values();
		Collection<Column<T>> pssColumns = new ArrayList<Column<T>>();
		pssColumns.add(table.primaryKey());

		String SQL = "SELECT #{columnNames} FROM #{table} WHERE #{pk}=?";
		SQL = replaceSql(SQL, "table", table.name());
		SQL = replaceSql(SQL, "columnNames", new SelectContents<T>(sqlColumns).toString());
		SQL = replaceSql(SQL, "pk", table.primaryKey().name());

		// List<T> list = jdbcTemplate.query(SQL, new
		// ObjectPreparedStatementSetter(pssColumns, object),
		// new ObjectRowMapper<T>(sqlColumns));
		List<T> list = jdbcTemplate.query(SQL, providePsSetter(pssColumns, object), new ObjectRowMapper<T>(sqlColumns));

		return list.size() > 0 ? (T) (list.get(0)) : null;
	}

	public T findById(K key) {
		T object = produceObject(key);
		return find(object);
	}

	public List<T> findAll() {
		Collection<Column<T>> columns = new ArrayList<Column<T>>();
		columns.add(table.primaryKey());

		String SQL = "SELECT #{pk} FROM #{table}";
		SQL = replaceSql(SQL, "table", table.name());
		SQL = replaceSql(SQL, "pk", table.primaryKey().name());

		// List<T> keyList = jdbcTemplate.query(SQL, new Object[] {}, new
		// ObjectRowMapper<T>(columns));
		List<T> keyList = jdbcTemplate.query(SQL, new ObjectRowMapper<T>(columns));
		List<T> list = new ArrayList<T>();
		for (T objectWithKey : keyList) {
			list.add(findById(fetchKey(objectWithKey)));
		}
		return list;
	}

	public void update(T object) {
		Collection<Column<T>> sqlColumns = filtColumns(table.values(),
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

	abstract protected void init();
	
	abstract protected void setJdbcTemplate(JdbcTemplate jdbcTemplate);

	abstract protected T produceObject(K key);

	abstract protected T produceObject();

	abstract protected K fetchKey(T object);

	protected Collection<Column<T>> filtColumns(Collection<Column<T>> source, ColumnsFilter<T> filter) {
		Collection<Column<T>> target = new ArrayList<Column<T>>();
		for (Column<T> column : source) {
			filter.doFilt(target, column);
		}
		return target;
	}

	protected String replaceSql(String sql, String target, String replacement) {
		String _sql = sql;
		_sql = _sql.replace("#{" + target + "}", replacement);
		return _sql;
	}

	protected PreparedStatementSetter providePsSetter(Collection<Column<T>> columns, T object) {
		PreparedStatementSetter setter = new ObjectPreparedStatementSetter(columns, object);
		return setter;
	}
	
	protected RowMapper<T> provideRowMapper(Collection<Column<T>> columns){
		RowMapper<T> rowMapper = new ObjectRowMapper<T>(columns);
		return rowMapper;
	}

	interface ColumnsFilter<T> {
		void doFilt(Collection<Column<T>> target, Column<T> column);
	}

	class ObjectRowMapper<T> implements RowMapper<T> {
		private Collection<Column<T>> columns;

		public ObjectRowMapper(Collection<Column<T>> columns) {
			super();
			this.columns = columns;
		}

		@Override
		public T mapRow(ResultSet rs, int rowNum) throws SQLException {
			T object = (T) produceObject();

			for (Column<T> column : columns) {
				try {
					column.fillObj(object, rs);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return object;
		}
	}

	class ObjectPreparedStatementSetter implements PreparedStatementSetter {

		private Collection<Column<T>> _columns;
		private T _object;

		public ObjectPreparedStatementSetter(Collection<Column<T>> _columns, T _object) {
			super();
			this._columns = _columns;
			this._object = _object;
		}

		@Override
		public void setValues(PreparedStatement ps) throws SQLException {
			Counter counter = new Counter();
			for (Column<T> column : _columns) {
				try {
					column.fillPs(ps, counter.next(), _object);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}

	class UpdateSetContents<T> extends SqlFragment {
		public UpdateSetContents(Collection<Column<T>> collection) {
			super();

			String setString = "";
			for (Column<T> column : collection) {
				setString += (column.name() + "=?,");
			}
			setString = setString.substring(0, setString.length() - 1);
			this.sql = setString;
		}
	}

	class InsertIntoContents<T> extends SqlFragment {
		public InsertIntoContents(Collection<Column<T>> collection) {
			super();

			String setString = "";
			for (Column<T> column : collection) {
				setString += (column.name() + ",");
			}
			setString = setString.substring(0, setString.length() - 1);
			this.sql = setString;
		}
	}

	class ValuesContents<T> extends SqlFragment {
		public ValuesContents(Collection<Column<T>> collection) {
			super();

			String setString = "";
			for (Column<T> column : collection) {
				setString += ("?,");
			}
			setString = setString.substring(0, setString.length() - 1);
			this.sql = setString;
		}
	}

	class SelectContents<T> extends SqlFragment {
		public SelectContents(Collection<Column<T>> collection) {
			super();

			String setString = "";
			for (Column<T> column : collection) {
				setString += (column.name() + ",");
			}
			setString = setString.substring(0, setString.length() - 1);
			this.sql = setString;
		}
	}

}
