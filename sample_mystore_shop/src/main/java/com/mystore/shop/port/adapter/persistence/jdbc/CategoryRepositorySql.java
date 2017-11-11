package com.mystore.shop.port.adapter.persistence.jdbc;

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
		categoryBaseSql.insert((CategoryBase) category);
	}

	@Override
	public void update(Category category) {
		categoryBaseSql.update((CategoryBase) category);
	}

	@Override
	public void delete(CategoryId categoryId) {
		categoryBaseSql.deleteById(categoryId);
	}

	@Override
	public List<Category> getList() throws Exception {
		List<CategoryBase> categoryBaseList = categoryBaseSql.findAll();
		return categoryFactory.categoryList(categoryBaseList);
	}

	@Override
	public Category get(CategoryId categoryId) throws Exception {
		CategoryBase categoryBase = categoryBaseSql.findOneById(categoryId);
		return categoryFactory.category(categoryBase);
	}

}
