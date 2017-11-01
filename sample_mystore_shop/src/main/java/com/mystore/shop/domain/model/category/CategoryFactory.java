package com.mystore.shop.domain.model.category;

import org.springframework.stereotype.Component;

@Component
public class CategoryFactory {

	public Category category(long categoryId, String name, String description){
		CategoryId _categoryId = new CategoryId(categoryId);
		Category _category = new Category(_categoryId,name,description);
		return _category;
	}
}
