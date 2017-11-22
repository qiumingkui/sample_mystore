package com.mystore.shop.domain.model.category;

import com.mystore.common.meta.domain.AggregateRootMeta;
import com.mystore.shop.port.adapter.persistence.jdbc.CategoryTable;

public class CategoryMeta extends AggregateRootMeta<Category> {

	public CategoryMeta(Class<Category> clazz, String identityObjectName) {
		super(clazz, identityObjectName);
		super.setTable(new CategoryTable());
	}
}
