package com.mystore.shop.port.adapter.persistence.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mystore.shop.domain.model.category.Category;
import com.mystore.shop.domain.model.category.CategoryBase;
import com.mystore.shop.domain.model.category.CategoryFactory;
import com.mystore.shop.domain.model.category.CategoryId;
import com.mystore.shop.domain.model.category.CategoryRepository;

@Component
public class CategoryRepositorySql implements CategoryRepository {

	@Autowired
	private CategoryFactory categoryFactory;

	@Autowired
	private CategoryBaseSql categoryBaseSql;

	@Override
	public void create(Category category) {

		categoryBaseSql.insert(translate(category));
	}

	@Override
	public void update(Category category) {

		categoryBaseSql.update(translate(category));
	}

	@Override
	public void delete(CategoryId categoryId) {

		categoryBaseSql.deleteById(categoryId);
	}

	@Override
	public List<Category> getList() throws Exception {

		List<CategoryBase> categoryBaseList = categoryBaseSql.findAll();

		return translateList(categoryBaseList);
	}

	@Override
	public Category get(CategoryId categoryId) throws Exception {

		CategoryBase categoryBase = categoryBaseSql.findOneById(categoryId);

		return translate(categoryBase);
	}

	private List<Category> translateList(List<CategoryBase> categoryBaseList) {

		List<Category> categoryList = new ArrayList<Category>();

		for (CategoryBase base : categoryBaseList) {

			categoryList.add(translate(base));
		}

		return categoryList;
	}

	private CategoryBase translate(Category category) {

		if (category == null)
			return null;

		CategoryBase categoryBase = new CategoryBase(category.categoryId(), category.name(), category.description());

		return categoryBase;
	}

	private Category translate(CategoryBase categoryBase) {

		if (categoryBase == null)
			return null;

		Category category = categoryFactory.category(categoryBase.getCategoryId(), categoryBase.getName(),
				categoryBase.getDescription());

		return category;
	}
}
