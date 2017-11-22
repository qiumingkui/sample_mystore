package com.mystore.shop.meta;

import com.mystore.common.meta.ClassMeta;
import com.mystore.common.meta.MetaLoader;
import com.mystore.common.meta.RegisterCenter;
import com.mystore.common.meta.domain.AggregateRootMeta;
import com.mystore.shop.domain.model.category.Category;
import com.mystore.shop.domain.model.category.CategoryBase;

public class CategoryMetaLoader implements MetaLoader<Category> {

	@Override
	public void loading(RegisterCenter registerCenter) {
		registerCenter.getAggregateRootMetaRegister()
				.register(new AggregateRootMeta<Category>(Category.class.getName(), CategoryBase.CATEGORYID));

		registerCenter.getClassMetaRegister().register(new ClassMeta<Category>(Category.class));

		registerCenter.getTableRegister().register(new CategoryTable());
	}

}
