package com.mystore.shop.domain.model.category;

import java.util.List;

public interface ProductRepository {

	public Category get(CategoryId categoryId);

	public void create(Category category);

	public void update(Category category);

	public void delete(CategoryId categoryId);

	public List<Category> getCategoryList();

}
