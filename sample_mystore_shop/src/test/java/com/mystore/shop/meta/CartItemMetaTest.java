package com.mystore.shop.meta;

import org.junit.Test;
import org.springframework.util.Assert;

import com.mystore.common.meta.ClassMeta;
import com.mystore.common.meta.MetaFactory;
import com.mystore.common.meta.domain.ValueObjectMeta;
import com.mystore.common.persistence.Table;
import com.mystore.shop.domain.model.cart.CartBase;
import com.mystore.shop.domain.model.cart.CartItem;

public class CartItemMetaTest {

	final MetaFactory metaFactory = new SysMetaFactory();
	final String CARTITEM_CLASSNAME = CartItem.class.getName();

	@Test
	public void test() {
		Table table = metaFactory.getTable(CARTITEM_CLASSNAME);
		Assert.isTrue(table.getTableName().equals(CartItemTable.TABLENAME));

		ValueObjectMeta<CartItem> aggregateRootMeta = metaFactory.getValueObjectMeta(CARTITEM_CLASSNAME);
		Assert.isTrue(aggregateRootMeta.getSupIdentityObjectFieldName().equals(CartBase.CARTID));

		ClassMeta<CartItem> classMeta = metaFactory.getClassMeta(CARTITEM_CLASSNAME);
		Assert.isTrue(classMeta.getField(CartItem.QUANTITY).getName().equals(CartItem.QUANTITY));
	}
}
