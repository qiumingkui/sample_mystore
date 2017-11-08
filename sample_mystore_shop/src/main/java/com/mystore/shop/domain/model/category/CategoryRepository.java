package com.mystore.shop.domain.model.category;

import java.util.List;

public interface CategoryRepository {

	public Category get(CategoryId categoryId) throws Exception;

	public void create(Category category);

	public void update(Category category);

	public void delete(CategoryId categoryId);

	public List<Category> getCategoryList() throws Exception;

}
