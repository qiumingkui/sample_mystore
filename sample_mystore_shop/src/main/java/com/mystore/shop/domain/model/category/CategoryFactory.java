package com.mystore.shop.domain.model.category;

public class CategoryFactory {

	public Category create(long categoryId, String name, String description){
		CategoryId _categoryId = new CategoryId(categoryId);
		Category _category = new Category(_categoryId,name,description);
		return _category;
	}
}
