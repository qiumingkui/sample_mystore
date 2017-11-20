package com.mystore.shop.port.adapter.persistence.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mystore.common.persistence.Table;
import com.mystore.shop.domain.model.category.Category;
import com.mystore.shop.domain.model.category.CategoryId;

public class CategoryTable extends Table<Category> {

	public static final String TABLENAME = "category";
	public static final String ID = "id";
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";

	@Override
	protected void init() {
		try {
			setClazz(Category.class);

			setTableName(TABLENAME);

			// add(ID, Category.CATEGORYID,
			// (PreparedStatement ps, int index, Category category) ->
			// ps.setLong(index,
			// ((CategoryId) getFieldPathValue(category,
			// Category.CATEGORYID)).getId()),
			// (Category category, ResultSet rs) -> setFieldPathValue(category,
			// Category.CATEGORYID,
			// new CategoryId(rs.getLong(ID))));
			// setPrimaryKay(ID);

			add(ID, Category.CATEGORYID + "." + CategoryId.ID);
			setPrimaryKay(ID);

			add(NAME, Category.NAME);

			add(DESCRIPTION, Category.DESCRIPTION);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
