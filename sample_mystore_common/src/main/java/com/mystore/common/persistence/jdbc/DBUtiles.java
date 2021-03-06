package com.mystore.common.persistence.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Component;

import com.mystore.common.persistence.Column;

@Component
public class DBUtiles<T> {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void operate(String sql, Collection<Column<T>> collection, T object) {
		jdbcTemplate.update(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				Counter counter = new Counter();
				for (Column<T> column : collection) {
					try {
						column.fillPs(ps, counter.next(), object);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
	}

	public void operate(String sql, Object... args) {
		jdbcTemplate.update(sql, args);
	}

	// public List<T> query(Class<T> clazz, String sql, Collection<Column<T>>
	// collection, Object[] args) throws Exception {
	// List<T> list = jdbcTemplate.query(sql, args, new RowMapper<T>() {
	// @Override
	// public T mapRow(ResultSet rs, int rowNum) throws SQLException {
	// T object = null;
	// // Class<? extends Object> clazz = object.getClass();
	// try {
	// object = (T) clazz.newInstance();
	// } catch (InstantiationException | IllegalAccessException e) {
	// e.printStackTrace();
	// }
	//
	// for (Column<T> column : collection) {
	// try {
	// column.fillKey(object, rs);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	// return object;
	// }
	// });
	// return list;
	// }

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

}
