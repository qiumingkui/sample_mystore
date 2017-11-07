package com.mystore.shop.port.adapter.persistence.repository.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.mystore.common.persistence.Column;

public class JdbcHelper<T> {

	public void operate(String sql, Collection<Column<T>> collection, T object,
			JdbcTemplate jdbcTemplate) {
		jdbcTemplate.update(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				Counter counter = new Counter();
				for (Column<T> column : collection) {
					column.fill(ps, counter.next(), object);
				}
			}
		});
	}

}
