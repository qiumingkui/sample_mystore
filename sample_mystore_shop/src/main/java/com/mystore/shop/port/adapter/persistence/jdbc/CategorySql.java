package com.mystore.shop.port.adapter.persistence.jdbc;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.mystore.common.persistence.Column;
import com.mystore.common.persistence.jdbc.JdbcEntityDao;
import com.mystore.shop.domain.model.category.Category;
import com.mystore.shop.domain.model.category.CategoryId;
import com.mystore.shop.domain.model.category.Page;

@Component
public class CategorySql extends JdbcEntityDao<Category, CategoryId> {

	public List<Category> findAllByNameLike(String nameValue) {
		List<CategoryId> categoryIds = findAllIdByNameLike(nameValue);
		List<Category> categorys = findAll(categoryIds);
		return categorys;
	}

	public List<CategoryId> findAllIdByNameLike(String name) {
		Collection<Column<Category>> rsColumns = new ArrayList<Column<Category>>();
		rsColumns.add(table.primaryKey());

		String SQL = "SELECT #{pk} FROM #{table} WHERE #{name} LIKE ?";
		SQL = sqlSetting(SQL, "table", table.name());
		SQL = sqlSetting(SQL, "pk", table.primaryKey().name());
		SQL = sqlSetting(SQL, "name", table.column(CategoryTable.NAME).name());

		List<Category> objectWithIdList = jdbcTemplate.query(SQL, new Object[] { "%" + name + "%" },
				provideRowMapper(rsColumns));

		return fetchIdList(objectWithIdList);
	}

	public Page<Category> page(int start, int size) {
		Page<CategoryId> categoryIdPage = pageId(start, size);
		List<Category> categoryList = findAll(categoryIdPage);
		Page<Category> page = new Page<Category>(categoryIdPage.getStart(), categoryIdPage.getSize(),
				categoryIdPage.getCount());
		page.addAll(categoryList);
		return page;
	}

	public Page<CategoryId> pageId(int start, int size) {
		Collection<Column<Category>> rsColumns = new ArrayList<Column<Category>>();
		rsColumns.add(table.primaryKey());

		String COUNT_SQL = "SELECT COUNT(#{pk}) c FROM #{table} LIMIT ?,?";
		COUNT_SQL = sqlSetting(COUNT_SQL, "table", table.name());
		COUNT_SQL = sqlSetting(COUNT_SQL, "pk", table.primaryKey().name());
		Map<String, Object> map = jdbcTemplate.queryForMap(COUNT_SQL, new Object[] { start, size });
		long count = (Long) map.get("c");

		String SQL = "SELECT #{pk} FROM #{table} LIMIT ?,?";
		SQL = sqlSetting(SQL, "table", table.name());
		SQL = sqlSetting(SQL, "pk", table.primaryKey().name());

		List<Category> objectWithIdList = jdbcTemplate.query(SQL, new Object[] { start, size },
				provideRowMapper(rsColumns));

		List<CategoryId> idList = fetchIdList(objectWithIdList);

		Page<CategoryId> page = new Page<CategoryId>(start, size, count);
		page.addAll(idList);

		return page;
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
	protected Category produceObject(CategoryId id) {

		// // category.setCategoryId(key);
		// try {
		// PropertyUtils.setProperty(category, "categoryId", key);
		// } catch (IllegalAccessException | InvocationTargetException |
		// NoSuchMethodException e) {
		// e.printStackTrace();
		// }

		try {
			Category category = produceObject();
			Field field;
			field = category.getClass().getDeclaredField("categoryId");
			field.setAccessible(true);

			try {
				field.set(category, id);
				return category;

			} catch (IllegalArgumentException e) {

				e.printStackTrace();

			} catch (IllegalAccessException e) {

				e.printStackTrace();
			}

		} catch (NoSuchFieldException | SecurityException e) {

			e.printStackTrace();
		}

		return null;
	}

	@Override
	protected Category produceObject() {

		try {
			Class<Category> clazz = Category.class;
			Constructor<Category> declaredConstructor = clazz.getDeclaredConstructor(null);
			declaredConstructor.setAccessible(true);
			Category category = declaredConstructor.newInstance(null);

			return category;

		} catch (NoSuchMethodException e) {

			e.printStackTrace();

		} catch (IllegalAccessException e) {

			e.printStackTrace();

		} catch (InvocationTargetException e) {

			e.printStackTrace();

		} catch (InstantiationException e) {

			e.printStackTrace();
		}

		return null;
	}

	@Override
	protected CategoryId fetchID(Category object) {
		CategoryId categoryId = null;
		try {
			final String CATEGORYID = "categoryId";
			try {
				Field field = object.getClass().getDeclaredField(CATEGORYID);
				field.setAccessible(true);
				categoryId = (CategoryId) field.get(object);
			} catch (NoSuchFieldException | SecurityException e) {
				e.printStackTrace();
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return categoryId;
	}
}
