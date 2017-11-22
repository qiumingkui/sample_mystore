package com.mystore.common.persistence.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import com.mystore.common.persistence.Column;
import com.mystore.common.persistence.Table;
import com.mystore.common.persistence.jdbc.sql.SqlFragment;
import com.mystore.common.utils.SimpleBeanUtil;

public abstract class JdbcBaseDao<T> {

	protected Table<T> table;
	protected JdbcTemplate jdbcTemplate;

	abstract protected void initTable();

	abstract protected void setJdbcTemplate(JdbcTemplate jdbcTemplate);

	abstract protected T produceObject();

	// protected T produceObject() {
	// return (T) SimpleBeanUtil.newInstance(table.getClazz());
	// }

	protected Collection<Column<T>> filtColumns(Collection<Column<T>> source, ColumnsFilter<T> filter) {
		Collection<Column<T>> target = new ArrayList<Column<T>>();
		for (Column<T> column : source) {
			filter.doFilt(target, column);
		}
		return target;
	}

	protected String sqlSetting(String sql, String target, String replacement) {
		String _sql = sql;
		_sql = _sql.replace("#{" + target + "}", replacement);
		return _sql;
	}

	protected PreparedStatementSetter providePsSetter(Collection<Column<T>> columns, T object) {
		PreparedStatementSetter setter = new ObjectPreparedStatementSetter(columns, object);
		return setter;
	}

	protected RowMapper<T> provideRowMapper(Collection<Column<T>> columns) {
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
				setString += (column.getColumnName() + "=?,");
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
				setString += (column.getColumnName() + ",");
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
				setString += (column.getColumnName() + ",");
			}
			setString = setString.substring(0, setString.length() - 1);
			this.sql = setString;
		}
	}

}
