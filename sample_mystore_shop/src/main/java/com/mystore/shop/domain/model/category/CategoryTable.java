package com.mystore.shop.domain.model.category;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mystore.common.persistence.Column;
import com.mystore.common.persistence.Table;

public class CategoryTable extends Table<CategoryBase> {

	private static final long serialVersionUID = 1L;

	protected void init() {
		setName("category");

		Column<CategoryBase> id = add("id",
				(PreparedStatement ps, int index, CategoryBase category) -> ps.setLong(index,
						category.getCategoryId().getId()),
				(CategoryBase category, ResultSet rs) -> category.getCategoryId().setId(rs.getLong("id")));
		id.setPrimaryKay();

		Column<CategoryBase> name = add("name",
				(PreparedStatement ps, int index, CategoryBase category) -> ps.setString(index, category.getName()),
				(CategoryBase category, ResultSet rs) -> category.setName(rs.getString("name")));

		Column<CategoryBase> description = add("description",
				(PreparedStatement ps, int index, CategoryBase category) -> ps.setString(index,
						category.getDescription()),
				(CategoryBase category, ResultSet rs) -> category.setDescription(rs.getString("description")));
	}
}
