package com.mystore.shop.domain.model.category;

import org.springframework.stereotype.Component;

@Component
public class CategoryFactory {

	public Category category(CategoryId categoryId, String name, String description) {

		// Category category = newProxyInstance(new CategoryModel(categoryId,
		// name, description));
		Category category = new Category(categoryId, name, description);

		return category;
	}

	// private Category newProxyInstance(Category category) {
	//
	// if (category == null)
	// return null;
	//
	// return (Category) Proxy.newProxyInstance(Category.class.getClassLoader(),
	// new Class[] { Category.class },
	// new EntityProxyHandler(category));
	// }
}