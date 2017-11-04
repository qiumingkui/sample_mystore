package com.mystore.shop.port.adapter.persistence.repository.jdbc;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;
import java.util.Random;

import javax.persistence.Column;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mystore.shop.domain.model.category.Category;
import com.mystore.shop.domain.model.category.CategoryFactory;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Obj2SQLTest {

	// public static void main(String[] args) {
	// Class clazz = Category.class;
	// Field[] fields = clazz.getFields();
	// for(Field field:fields){
	// AnnotationType a = field.getAnnotationsByType(Column.class);
	// field.geta
	// }

	@Autowired
	private CategoryFactory _categoryFactory;

	@Test
	public void bean2Map() {
		Category newObj = newCategory();
		Map<String, Object> map = null;
		try {
			map = PropertyUtils.describe(newObj);
			// Collection c= map.values();
			// for(Object o:c){
			// System.out.println(o);
			// }
			System.out.println("name=" + map.get("name"));
			System.out.println("id=" + map.get("categoryId"));
		} catch (Exception e) {
			e.getMessage();
		}
	}

	private Category newCategory() {
		Random random = new Random();
		Long id = random.nextLong();
		Category category = _categoryFactory.category(id, "book", "This is book!");
		return category;
	}
}
