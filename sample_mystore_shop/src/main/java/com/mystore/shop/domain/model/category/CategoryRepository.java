package com.mystore.shop.domain.model.category;

import java.util.List;

public interface CategoryRepository {

	public Category get(CategoryId categoryId);

	public void save(Category category);

	public void delete(CategoryId categoryId);

	List<Category> getCategoryList();

}
