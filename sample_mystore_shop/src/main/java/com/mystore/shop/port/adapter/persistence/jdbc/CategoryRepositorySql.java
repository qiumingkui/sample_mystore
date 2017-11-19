package com.mystore.shop.port.adapter.persistence.jdbc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mystore.shop.domain.model.category.Category;
import com.mystore.shop.domain.model.category.CategoryId;
import com.mystore.shop.domain.model.category.CategoryRepository;
import com.mystore.shop.domain.model.category.Page;

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
	public Category get(CategoryId categoryId) throws Exception {

		Category category = categorySql.findOneById(categoryId);

		return category;
	}

	@Override
	public List<Category> list() throws Exception {

		List<Category> categoryList = categorySql.findAll();

		return categoryList;
	}

	@Override
	public Page<Category> page(int index, int size) {
		
		List<Category> categorys = categorySql.page(index, size);
		Page<Category> page = new Page<Category>(index, size, 0);
		page.addAll(categorys);
		
		return page;
	}

}
