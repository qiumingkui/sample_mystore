package com.mystore.shop.port.adapter.persistence.jdbc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.mystore.common.persistence.Column;
import com.mystore.common.persistence.jdbc.JdbcEntityDao;
import com.mystore.shop.domain.model.category.CategoryBase;
import com.mystore.shop.domain.model.category.CategoryId;

@Component
public class CategoryBaseSql extends JdbcEntityDao<CategoryBase, CategoryId> {

	public List<CategoryBase> findAllByNameLike(String nameValue) {
		List<CategoryId> categoryIds = findAllIdByNameLike(nameValue);
		List<CategoryBase> categoryBases = findAll(categoryIds);
		return categoryBases;
	}

	public List<CategoryId> findAllIdByNameLike(String name) {
		Collection<Column<CategoryBase>> rsColumns = new ArrayList<Column<CategoryBase>>();
		rsColumns.add(table.primaryKey());

		String SQL = "SELECT #{pk} FROM #{table} WHERE #{name} LIKE ?";
		SQL = sqlSetting(SQL, "table", table.name());
		SQL = sqlSetting(SQL, "pk", table.primaryKey().name());
		SQL = sqlSetting(SQL, "name", table.column(CategoryTable.NAME).name());

		List<CategoryBase> objectWithKeyList = jdbcTemplate.query(SQL, new Object[] { "%" + name + "%" },
				provideRowMapper(rsColumns));

		return fetchKeyList(objectWithKeyList);
	}

	@Autowired
	@Override
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
