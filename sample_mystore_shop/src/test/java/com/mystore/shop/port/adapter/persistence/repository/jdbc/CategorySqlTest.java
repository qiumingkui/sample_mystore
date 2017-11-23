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
import com.mystore.shop.domain.model.category.CategoryId;
import com.mystore.shop.domain.model.category.Page;
import com.mystore.shop.port.adapter.persistence.jdbc.CategorySql;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategorySqlTest {

	private static final String CHANGED_DES = "This is changed Book!";

	private static final String BOOK = "book";
	private static final String PEN = "pen";

	@Autowired
	private CategorySql _categorySql;

	@Autowired
	private CategoryFactory categoryFactory;

	@Test
	public void test() throws Exception {
		Category newObj = newCategory();
		_categorySql.insert(newObj);
		Category retrievedObj = _categorySql.findOneById(newObj.categoryId());
		assertFalse(retrievedObj == null);

		retrievedObj.changeDescription(CHANGED_DES);
		_categorySql.update(retrievedObj);
		Category updatedObj = _categorySql.findOneById(retrievedObj.categoryId());
		assertFalse(updatedObj == null);
		assertFalse(!updatedObj.description().equals(CHANGED_DES));

		List<Category> likeNameList = _categorySql.findAllByNameLike(BOOK);
		assertFalse(likeNameList.size() <= 0);
		likeNameList = _categorySql.findAllByNameLike(PEN);
		assertFalse(likeNameList.size() > 0);

		List<Category> list = _categorySql.findAll();
		assertFalse(list.size() <= 0);
		Category firstObj = list.get(0);
		assertFalse(firstObj == null);
		assertFalse(!updatedObj.description().equals(CHANGED_DES));

		Page<Category> page = _categorySql.page(0, 10);
		assertFalse(page.size() <= 0);

		_categorySql.deleteById(retrievedObj.categoryId());
		Category deletedObj = _categorySql.findOneById(retrievedObj.categoryId());
		assertFalse(deletedObj != null);
	}

	private Category newCategory() {
		Random random = new Random();
		Long id = random.nextLong();
		// Category category = new Category(new CategoryId(id), "book", "This is
		// book!");
		Category category = categoryFactory.category(new CategoryId(id), "book", "This is book!");
		return category;
	}
}
