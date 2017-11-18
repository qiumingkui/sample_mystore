package com.mystore.shop.port.adapter.persistence.jdbc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mystore.shop.domain.model.category.Category;
import com.mystore.shop.domain.model.category.CategoryId;
import com.mystore.shop.domain.model.category.CategoryRepository;

@Component
public class CategoryRepositorySql implements CategoryRepository {

	@Autowired
	private CategorySql categorySql;

	@Override
	public void create(Category category) {

		categorySql.insert(category);
	}

	@Override
	public void update(Category category) {

		categorySql.update(category);
	}

	@Override
	public void delete(CategoryId categoryId) {

		categorySql.deleteById(categoryId);
	}

	@Override
	public List<Category> getList() throws Exception {

		List<Category> categoryList = categorySql.findAll();

		return categoryList;
	}

	@Override
	public Category get(CategoryId categoryId) throws Exception {

		Category category = categorySql.findOneById(categoryId);

		return category;
	}
}
