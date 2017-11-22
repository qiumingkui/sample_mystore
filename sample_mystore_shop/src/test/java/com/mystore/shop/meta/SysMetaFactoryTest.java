package com.mystore.shop.meta;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import com.mystore.common.meta.ClassMeta;
import com.mystore.common.meta.MetaFactory;
import com.mystore.shop.domain.model.category.Category;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SysMetaFactoryTest {

	// private MetaFactory metaFactory = new SysMetaFactory();
	// @Autowired
	// private MetaFactory metaFactory;

	private MetaFactory metaFactory = SysMetaFactory.instance();

	@Test
	public void test() {
		ClassMeta classMeta = metaFactory.getClassMeta(Category.class.getName());
		Assert.isTrue(classMeta.getName().equals(Category.class.getName()));
	}
}
