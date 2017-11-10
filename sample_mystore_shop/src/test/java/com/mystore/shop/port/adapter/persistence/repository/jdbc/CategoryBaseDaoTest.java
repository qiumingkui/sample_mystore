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
import com.mystore.shop.domain.model.category.CategoryId;
import com.mystore.shop.port.adapter.persistence.jdbc.CategoryBaseSql;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryBaseDaoTest {

	private static final String CHANGED_DES = "This is changed Book!";

	@Autowired
	private CategoryFactory _categoryFactory;

	@Autowired
	private CategoryBaseSql _categoryBaseDao;

	@Test
	public void test() throws Exception {
		CategoryBase newObj = newCategory();
		_categoryBaseDao.insert(newObj);
		CategoryBase retrievedObj = _categoryBaseDao.findById(newObj.getCategoryId());
		assertFalse(retrievedObj == null);

		retrievedObj.setDescription(CHANGED_DES);
		_categoryBaseDao.update(retrievedObj);
		CategoryBase updatedObj = _categoryBaseDao.findById(retrievedObj.getCategoryId());
		assertFalse(updatedObj == null);
		assertFalse(!updatedObj.getDescription().equals(CHANGED_DES));

		List<CategoryBase> list = _categoryBaseDao.findAll();
		assertFalse(list.size() <= 0);
		CategoryBase firstObj = list.get(0);
		assertFalse(firstObj == null);
		assertFalse(!updatedObj.getDescription().equals(CHANGED_DES));

		_categoryBaseDao.deleteById(retrievedObj.getCategoryId());
		CategoryBase deletedObj = _categoryBaseDao.findById(retrievedObj.getCategoryId());
		assertFalse(deletedObj != null);
	}

	// @Test
	// public void del() throws Exception {
	// CategoryBase newObj = newCategory();
	// _categoryBaseDao.insert(newObj);
	// CategoryBase retrievedObj =
	// _categoryBaseDao.findById(newObj.getCategoryId());
	// assertFalse(retrievedObj == null);
	//
	// _categoryBaseDao.deleteById(retrievedObj.getCategoryId());
	// CategoryBase deletedObj =
	// _categoryBaseDao.findById(retrievedObj.getCategoryId());
	// assertFalse(deletedObj != null);
	// }

	private CategoryBase newCategory() {
		Random random = new Random();
		Long id = random.nextLong();
		Category category = _categoryFactory.category(new CategoryId(id), "book", "This is book!");
		return (CategoryBase) category;
	}
}
