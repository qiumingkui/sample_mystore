package com.mystore.shop.port.adapter.persistence.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.mystore.common.persistence.Column;
import com.mystore.common.persistence.Table;
import com.mystore.common.persistence.jdbc.sql.InsertIntoContents;
import com.mystore.common.persistence.jdbc.sql.SQL;
import com.mystore.common.persistence.jdbc.sql.ValuesContents;
import com.mystore.shop.domain.model.category.Category;
import com.mystore.shop.domain.model.category.CategoryFactory;
import com.mystore.shop.domain.model.category.CategoryId;
import com.mystore.shop.domain.model.category.CategoryRepository;
import com.mystore.shop.domain.model.category.CategoryTable;
import com.mystore.shop.port.adapter.persistence.repository.jdbc.SqlGen;
import com.mystore.shop.port.adapter.persistence.repository.jdbc.Counter;
import com.mystore.shop.port.adapter.persistence.repository.jdbc.EntitySqlGenFactory;

@Component
public class CategoryRepositorySql2 implements CategoryRepository {

	private static final EntitySqlGenFactory<Category> entitySqlGenFactory = new EntitySqlGenFactory<Category>();
	private static final CategoryTable t = new CategoryTable();
	private final String INSERT = entitySqlGenFactory.insertSqlGen().sql(t);
	private final String DELETE = entitySqlGenFactory.deleteSqlGen().sql(t);
	private final String UPDATE = entitySqlGenFactory.updateSqlGen().sql(t);
	private final String SELECT = entitySqlGenFactory.selectSqlGen().sql(t);

	@Autowired
	private CategoryFactory categoryFactory;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void create(Category category) {
		Collection<Column<Category>> collection = t.values();
		operation(INSERT, collection, category);
	}

	public void operation(String sql, Collection<Column<Category>> collection, Category category){
		jdbcTemplate.update(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				Counter counter = new Counter();
				for (Column<Category> column : collection) {
					column.assign(ps, counter.next(), category);
				}
			}
		});
	}
	
	// @Override
	// public void create(Category category) {
	// final String SQL = "INSERT INTO category(id,name,description)
	// VALUES(?,?,?)";
	// jdbcTemplate.update(SQL, new PreparedStatementSetter() {
	// @Override
	// public void setValues(PreparedStatement ps) throws SQLException {
	// ps.setLong(1, category.categoryId().id());
	// ps.setString(2, category.name());
	// ps.setString(3, category.description());
	// }
	// });
	// }

	@Override
	public void update(Category category) {
		final String SQL = "UPDATE category SET name=?,description=? WHERE id=?";
		jdbcTemplate.update(SQL, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, category.name());
				ps.setString(2, category.description());
				ps.setLong(3, category.categoryId().id());
			}
		});
	}

	@Override
	public void delete(CategoryId categoryId) {
		final String SQL = "DELETE FROM category WHERE id=?";
		jdbcTemplate.update(SQL, categoryId.id());
	}

	@Override
	public Category get(CategoryId categoryId) {
		final String SQL = "SELECT * FROM category WHERE id=?";
		List<Category> categoryList = jdbcTemplate.query(SQL, new Object[] { categoryId.id() },
				new CategoryRowMapper());
		return categoryList.size() > 0 ? categoryList.get(0) : null;
	}

	@Override
	public List<Category> getCategoryList() {
		final String SQL = "SELECT id FROM category";
		List<CategoryId> categoryIdList = jdbcTemplate.query(SQL, new CategoryIdRowMapper());
		List<Category> categoryList = new ArrayList<Category>();
		for (CategoryId categoryId : categoryIdList) {
			Category category = get(categoryId);
			categoryList.add(category);
		}
		return categoryList;
	}

	class CategoryIdRowMapper implements RowMapper<CategoryId> {
		@Override
		public CategoryId mapRow(ResultSet rs, int rowNum) throws SQLException {
			CategoryId id = new CategoryId(rs.getLong("id"));
			return id;
		}
	}

	class CategoryRowMapper implements RowMapper<Category> {
		@Override
		public Category mapRow(ResultSet rs, int i) throws SQLException {
			long id = rs.getLong("id");
			String name = rs.getString("name");
			String description = rs.getString("description");
			Category category = categoryFactory.category(id, name, description);
			return category;
		}
	}

	class CategoryMapRowMapper implements RowMapper<Map<String, Object>> {
		@Override
		public Map<String, Object> mapRow(ResultSet rs, int i) throws SQLException {
			long id = rs.getLong("id");
			String name = rs.getString("name");
			String description = rs.getString("description");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", id);
			map.put("name", name);
			map.put("description", description);
			return map;
		}
	}

}
