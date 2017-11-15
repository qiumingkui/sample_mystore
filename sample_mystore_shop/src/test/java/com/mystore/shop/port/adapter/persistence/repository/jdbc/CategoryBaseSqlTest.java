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
public class CategoryBaseSqlTest {

	private static final String CHANGED_DES = "This is changed Book!";

	private static final String BOOK = "book";
	private static final String PEN = "pen";

	@Autowired
	private CategoryFactory _categoryFactory;

	@Autowired
	private CategoryBaseSql _categoryBaseSql;

	@Test
	public void test() throws Exception {
		CategoryBase newObj = newCategory();
		_categoryBaseSql.insert(newObj);
		CategoryBase retrievedObj = _categoryBaseSql.findOneById(newObj.getCategoryId());
		assertFalse(retrievedObj == null);

		retrievedObj.setDescription(CHANGED_DES);
		_categoryBaseSql.update(retrievedObj);
		CategoryBase updatedObj = _categoryBaseSql.findOneById(retrievedObj.getCategoryId());
		assertFalse(updatedObj == null);
		assertFalse(!updatedObj.getDescription().equals(CHANGED_DES));

		List<CategoryBase> likeNameList = _categoryBaseSql.findAllByNameLike(BOOK);
		assertFalse(likeNameList.size() <= 0);
		likeNameList = _categoryBaseSql.findAllByNameLike(PEN);
		assertFalse(likeNameList.size() > 0);

		List<CategoryBase> list = _categoryBaseSql.findAll();
		assertFalse(list.size() <= 0);
		CategoryBase firstObj = list.get(0);
		assertFalse(firstObj == null);
		assertFalse(!updatedObj.getDescription().equals(CHANGED_DES));

		_categoryBaseSql.deleteById(retrievedObj.getCategoryId());
		CategoryBase deletedObj = _categoryBaseSql.findOneById(retrievedObj.getCategoryId());
		assertFalse(deletedObj != null);
	}

	private CategoryBase newCategory() {
		Random random = new Random();
		Long id = random.nextLong();
		Category category = _categoryFactory.category(new CategoryId(id), "book", "This is book!");
		return category.categoryBase();
	}
}
