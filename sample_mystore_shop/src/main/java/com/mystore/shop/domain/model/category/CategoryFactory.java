package com.mystore.shop.domain.model.category;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class CategoryFactory {

	// public Category category(long categoryId, String name, String
	// description) {
	// CategoryId _categoryId = new CategoryId(categoryId);
	// Category _category = new CategoryModel(_categoryId, name, description);
	// return _category;
	// }

	public Category category(CategoryId categoryId, String name, String description) {
		Category _category = new CategoryModel(categoryId, name, description);
		return _category;
	}

	@SuppressWarnings("unused")
	public Category category(CategoryBase categoryBase) {
		CategoryModel categoryModel = new CategoryModel();
		if (categoryBase == null)
			return null;
		BeanUtils.copyProperties(categoryBase, categoryModel);
		return categoryModel;
	}

	public List<Category> categoryList(List<CategoryBase> categoryBaseList) {
		List<Category> categoryList = new ArrayList<Category>();
		for (CategoryBase base : categoryBaseList) {
			categoryList.add(category(base));
		}
		return categoryList;
	}
}
