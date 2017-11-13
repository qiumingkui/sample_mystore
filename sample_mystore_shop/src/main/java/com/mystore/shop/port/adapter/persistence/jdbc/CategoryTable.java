package com.mystore.shop.port.adapter.persistence.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mystore.common.persistence.Table;
import com.mystore.shop.domain.model.category.CategoryBase;
import com.mystore.shop.domain.model.category.CategoryId;

public class CategoryTable extends Table<CategoryBase> {

	private static final long serialVersionUID = 1L;

	public static final String TABLENAME="category";
	public static final String ID = "id";
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";

	@Override
	protected void init() {
		try {
			setTableName(TABLENAME);

			add(ID, (PreparedStatement ps, int index, CategoryBase category) -> ps.setLong(index,
					category.getCategoryId().getId()),
					(CategoryBase category, ResultSet rs) -> category.setCategoryId(new CategoryId(rs.getLong(ID))));
			setPrimaryKay(ID);
			// id.setPrimaryKay();

			add(NAME,
					(PreparedStatement ps, int index, CategoryBase category) -> ps.setString(index, category.getName()),
					(CategoryBase category, ResultSet rs) -> category.setName(rs.getString(NAME)));

			add(DESCRIPTION,
					(PreparedStatement ps, int index, CategoryBase category) -> ps.setString(index,
							category.getDescription()),
					(CategoryBase category, ResultSet rs) -> category.setDescription(rs.getString(DESCRIPTION)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
