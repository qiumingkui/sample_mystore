package com.mystore.shop.meta;

import com.mystore.common.persistence.Table;
import com.mystore.shop.domain.model.category.Category;
import com.mystore.shop.domain.model.category.CategoryBase;
import com.mystore.shop.domain.model.category.CategoryId;

public class CategoryTable extends Table<Category> {

	public static final String TABLENAME = "category";
	public static final String ID = "id";
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String CURRENTVERSION = "currentversion";

	@Override
	protected void init() {
		try {
			setClassName(Category.class.getName());

			setTableName(TABLENAME);

			add(ID, CategoryBase.CATEGORYID + "." + CategoryId.ID);
			setPrimaryKay(ID);

			add(NAME, CategoryBase.NAME);

			add(DESCRIPTION, CategoryBase.DESCRIPTION);

			add(CURRENTVERSION, CategoryBase.CURRENTVERSION);
			setVersion(CURRENTVERSION);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
