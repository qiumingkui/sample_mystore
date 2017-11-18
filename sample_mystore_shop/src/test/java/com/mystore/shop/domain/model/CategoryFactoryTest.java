package com.mystore.shop.domain.model;

import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Test;

import com.mystore.shop.domain.model.category.Category;
import com.mystore.shop.domain.model.category.CategoryFactory;
import com.mystore.shop.domain.model.category.CategoryId;

public class CategoryFactoryTest {

	private CategoryFactory categoryFactory = new CategoryFactory();

	@Test
	public void cateory() {
		Category category = newCategory();
		assertTrue(category != null);
		assertTrue(category.name().equals("book"));

	}

	private Category newCategory() {
		Random random = new Random();
		Long id = random.nextLong();
		Category category = categoryFactory.category(new CategoryId(id), "book", "This is book!");
		return category;
	}
}
