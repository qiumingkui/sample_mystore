package com.mystore.shop.domain.model.category;

import com.mystore.common.meta.domain.AggregateRootMeta;

public class CategoryClassMeta extends AggregateRootMeta<Category> {

	public CategoryClassMeta(String className, String identityObjectFieldName) {
		super(className, identityObjectFieldName);
	}
}
