package com.mystore.shop.port.adapter.persistence.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.mystore.common.persistence.Table;
import com.mystore.common.persistence.jdbc.JdbcCurdDao;
import com.mystore.shop.domain.model.category.CategoryBase;
import com.mystore.shop.domain.model.category.CategoryId;
import com.mystore.shop.domain.model.category.CategoryTable;

@Component
public class CategoryBaseSql extends JdbcCurdDao<CategoryBase, CategoryId> {

	@Autowired
	protected void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	protected void init() {
		this.table = new CategoryTable();
	}

	@Override
	protected CategoryBase produceObject(CategoryId key) {
		CategoryBase categoryBase = produceObject();
		categoryBase.setCategoryId(key);
		return categoryBase;
	}

	@Override
	protected CategoryBase produceObject() {
		CategoryBase categoryBase = new CategoryBase();
		return categoryBase;
	}

	@Override
	protected CategoryId fetchKey(CategoryBase object) {
		CategoryId categoryId = object.getCategoryId();
		return categoryId;
	}

}
