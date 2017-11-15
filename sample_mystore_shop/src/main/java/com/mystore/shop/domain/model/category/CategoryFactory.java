package com.mystore.shop.domain.model.category;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.mystore.common.dynamicproxy.EntityProxyHandler;

@Component
public class CategoryFactory {

	public Category category(CategoryId categoryId, String name, String description) {

		Category category = newProxyInstance(new CategoryModel(categoryId, name, description));

		return category;
	}

	public Category category(CategoryBase categoryBase) {

		if (categoryBase == null)
			return null;

		Category category = newProxyInstance(new CategoryModel(categoryBase));

		return category;
	}

	public List<Category> categoryList(List<CategoryBase> categoryBaseList) {

		List<Category> categoryList = new ArrayList<Category>();

		for (CategoryBase base : categoryBaseList) {

			categoryList.add(category(base));
		}

		return categoryList;
	}

	private Category newProxyInstance(Category category) {

		if (category == null)
			return null;

		return (Category) Proxy.newProxyInstance(Category.class.getClassLoader(), new Class[] { Category.class },
				new EntityProxyHandler(category));
	}
}