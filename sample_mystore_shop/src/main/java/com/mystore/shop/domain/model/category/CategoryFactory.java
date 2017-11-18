package com.mystore.shop.domain.model.category;

import java.lang.reflect.Proxy;
import org.springframework.stereotype.Component;

import com.mystore.common.aop.EntityProxyHandler;

@Component
public class CategoryFactory {

	public Category category(CategoryId categoryId, String name, String description) {

		Category category = newProxyInstance(new CategoryModel(categoryId, name, description));

		return category;
	}


	private Category newProxyInstance(Category category) {

		if (category == null)
			return null;

		return (Category) Proxy.newProxyInstance(Category.class.getClassLoader(), new Class[] { Category.class },
				new EntityProxyHandler(category));
	}
}