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
import com.mystore.shop.domain.model.category.CategoryBase;
import com.mystore.shop.domain.model.category.CategoryFactory;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryDaoTest {

	private static final String CHANGED_DES = "This is changed Book!";

	@Autowired
	private CategoryFactory _categoryFactory;

	@Autowired
	private CategoryDao _categoryDao;

	@Test
	public void covered() throws Exception {
		CategoryBase newObj = newCategory();
		_categoryDao.insert(newObj);
		CategoryBase retrievedObj = _categoryDao.find(newObj.getCategoryId().getId());
		assertFalse(retrievedObj == null);

		retrievedObj.setDescription(CHANGED_DES);
		_categoryDao.update(retrievedObj);
		CategoryBase updatedObj = _categoryDao.find(retrievedObj.getCategoryId().getId());
		assertFalse(updatedObj == null);
		assertFalse(!updatedObj.getDescription().equals(CHANGED_DES));

		List<CategoryBase> list = _categoryDao.findAll();
		assertFalse(list.size() <= 0);
		CategoryBase firstObj = list.get(0);
		assertFalse(firstObj == null);
		assertFalse(!updatedObj.getDescription().equals(CHANGED_DES));

	}

	@Test
	public void del() throws Exception {
		CategoryBase newObj = newCategory();
		_categoryDao.insert(newObj);
		CategoryBase retrievedObj = _categoryDao.find(newObj.getCategoryId().getId());
		assertFalse(retrievedObj == null);

		_categoryDao.delete(retrievedObj.getCategoryId().getId());
		CategoryBase deletedObj = _categoryDao.find(retrievedObj.getCategoryId().getId());
		assertFalse(deletedObj != null);
	}

	private CategoryBase newCategory() {
		Random random = new Random();
		Long id = random.nextLong();
		Category category = _categoryFactory.category(id, "book", "This is book!");
		return (CategoryBase) category;
	}
}
