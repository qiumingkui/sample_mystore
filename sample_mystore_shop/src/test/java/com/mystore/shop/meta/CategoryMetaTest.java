package com.mystore.shop.meta;

import org.junit.Test;
import org.springframework.util.Assert;

import com.mystore.common.meta.ClassMeta;
import com.mystore.common.meta.MetaFactory;
import com.mystore.common.meta.domain.AggregateRootMeta;
import com.mystore.common.persistence.Table;
import com.mystore.shop.domain.model.category.Category;
import com.mystore.shop.domain.model.category.CategoryBase;

public class CategoryMetaTest {

	final MetaFactory metaFactory = new SysMetaFactory();
	final String CATEGORY_CLASSNAME = Category.class.getName();

	@Test
	public void test() {
		Table table = metaFactory.getTable(CATEGORY_CLASSNAME);
		Assert.isTrue(table.getTableName().equals(CategoryTable.TABLENAME));

		AggregateRootMeta<Category> aggregateRootMeta = metaFactory.getAggregateRootMeta(CATEGORY_CLASSNAME);
		Assert.isTrue(aggregateRootMeta.getIdentityObjectFieldName().equals(CategoryBase.CATEGORYID));

		ClassMeta<Category> classMeta = metaFactory.getClassMeta(CATEGORY_CLASSNAME);
		Assert.isTrue(classMeta.getField(CategoryBase.NAME).getName().equals(CategoryBase.NAME));
	}
}
