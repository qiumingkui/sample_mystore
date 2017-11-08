package com.mystore.shop.port.adapter.persistence.repository.jdbc;

import static org.junit.Assert.assertFalse;

import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mystore.shop.domain.model.category.Category;
import com.mystore.shop.domain.model.category.CategoryFactory;
import com.mystore.shop.port.adapter.persistence.jdbc.CategoryRepositorySql;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryRepositorySqlTest {

	private static final String CHANGED_DES = "This is changed Book!";

	@Autowired
	private CategoryFactory _categoryFactory;

	@Autowired
	private CategoryRepositorySql _categoryRepository;

	@Test
	public void covered() throws Exception {
		Category newObj = newCategory();
		_categoryRepository.create(newObj);
		Category retrievedObj = _categoryRepository.get(newObj.categoryId());
		assertFalse(retrievedObj == null);

		retrievedObj.changeDescription(CHANGED_DES);
		_categoryRepository.update(retrievedObj);
		Category updatedObj = _categoryRepository.get(retrievedObj.categoryId());
		assertFalse(updatedObj == null);
		assertFalse(!updatedObj.description().equals(CHANGED_DES));

		List<Category> list = _categoryRepository.getCategoryList();
		assertFalse(list.size() <= 0);
		Category firstObj = list.get(0);
		assertFalse(firstObj == null);
		assertFalse(!updatedObj.description().equals(CHANGED_DES));

	}

	@Test
	public void del() throws Exception {
		Category newObj = newCategory();
		_categoryRepository.create(newObj);
		Category retrievedObj = _categoryRepository.get(newObj.categoryId());
		assertFalse(retrievedObj == null);

		_categoryRepository.delete(retrievedObj.categoryId());
		Category deletedObj = _categoryRepository.get(retrievedObj.categoryId());
		assertFalse(deletedObj != null);
	}

	private Category newCategory() {
		Random random = new Random();
		Long id = random.nextLong();
		Category category = _categoryFactory.category(id, "book", "This is book!");
		return category;
	}
}
