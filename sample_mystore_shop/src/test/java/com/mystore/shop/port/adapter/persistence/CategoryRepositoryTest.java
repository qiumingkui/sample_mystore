package com.mystore.shop.port.adapter.persistence;

import static org.junit.Assert.assertFalse;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mystore.shop.domain.model.category.Category;
import com.mystore.shop.domain.model.category.CategoryFactory;
import com.mystore.shop.domain.model.category.CategoryRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryRepositoryTest {

	
	private static final String CHANGED_DES = "This is changed Book!";

	@Autowired
	private CategoryFactory _categoryFactory;

	@Autowired
	private CategoryRepository _categoryRepository;

	@Test
	public void covered() {
		Category newObj = newCategory();
		_categoryRepository.create(newObj);
		Category retrievedObj = _categoryRepository.get(newObj.categoryId());
		assertFalse(retrievedObj==null);

		retrievedObj.changeDescription(CHANGED_DES);
		_categoryRepository.update(retrievedObj);
		Category updatedObj = _categoryRepository.get(retrievedObj.categoryId());
		assertFalse(updatedObj==null);
		assertFalse(!updatedObj.description().equals(CHANGED_DES));
		
		List<Category> list = _categoryRepository.getCategoryList();
		assertFalse(list.size()<=0);
		Category firstObj = list.get(0);
		assertFalse(firstObj==null);
		assertFalse(!updatedObj.description().equals(CHANGED_DES));
		
	}

	private Category newCategory() {
		Category category = _categoryFactory.category(1, "book", "This is book!");
		return category;
	}
}
