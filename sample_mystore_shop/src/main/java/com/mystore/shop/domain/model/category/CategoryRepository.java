package com.mystore.shop.domain.model.category;

import java.util.List;

abstract public class CategoryRepository {

	abstract public Category get(CategoryId categoryId);

	abstract public void save(Category category);

	abstract public void delete(CategoryId categoryId);

	abstract public List<Category> getCategoryList();
	
	protected Category rebuild(long categoryId, String name, String description){
		CategoryId _categoryId = new CategoryId(categoryId);
		Category _category = new Category(_categoryId,name,description);
		return _category;
	} 

}
