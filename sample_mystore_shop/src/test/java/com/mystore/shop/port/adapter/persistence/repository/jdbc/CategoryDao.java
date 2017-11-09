package com.mystore.shop.port.adapter.persistence.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.mystore.shop.domain.model.category.CategoryBase;
import com.mystore.shop.domain.model.category.CategoryTable;

@Component
public class CategoryDao extends JdbcCurdDao<CategoryBase, Long> {

	public CategoryDao() {
		super();
		super.table = new CategoryTable();
		super.clazz = CategoryBase.class;
	}

	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}
