package com.mystore.shop.port.adapter.persistence.jdbc;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
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
			setTableName(TABLENAME);

			add(ID, (PreparedStatement ps, int index, Category category) -> ps.setLong(index,
					((CategoryId) getProperty(category, "categoryId")).getId()),
					(Category category, ResultSet rs) -> setProperty(category, "categoryId",
							new CategoryId(rs.getLong(ID))));
			setPrimaryKay(ID);

			add(NAME,
					(PreparedStatement ps, int index, Category category) -> ps.setObject(index,
							getProperty(category, "name")),
					(Category category, ResultSet rs) -> setProperty(category, "name", rs.getObject(NAME)));

			add(DESCRIPTION,
					(PreparedStatement ps, int index, Category category) -> ps.setObject(index,
							getProperty(category, "description")),
					(Category category, ResultSet rs) -> setProperty(category, "description",
							rs.getObject(DESCRIPTION)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void setProperty(Category object, String name, Object value) throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException, NoSuchFieldException, SecurityException {
		Field field = object.getClass().getDeclaredField(name);
		field.setAccessible(true);
		field.set(object, value);
	}

	protected Object getProperty(Category object, String name) throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException, NoSuchFieldException, SecurityException {
		Field field = Category.class.getDeclaredField(name);
		field.setAccessible(true);
		return field.get(object);
	}
}
