package com.mystore.shop.meta;

import org.junit.Test;
import org.springframework.util.Assert;

import com.mystore.common.meta.ClassMeta;
import com.mystore.common.meta.MetaFactory;
import com.mystore.common.meta.domain.AggregateRootMeta;
import com.mystore.common.persistence.Table;
import com.mystore.shop.domain.model.cart.Cart;
import com.mystore.shop.domain.model.cart.CartBase;

public class CartMetaTest {

	final MetaFactory metaFactory = new SysMetaFactory();
	final String CART_CLASSNAME = Cart.class.getName();

	@Test
	public void test() {
		Table table = metaFactory.getTable(CART_CLASSNAME);
		Assert.isTrue(table.getTableName().equals(CartTable.TABLENAME));

		AggregateRootMeta<Cart> aggregateRootMeta = metaFactory.getAggregateRootMeta(CART_CLASSNAME);
		Assert.isTrue(aggregateRootMeta.getIdentityObjectFieldName().equals(CartBase.CARTID));

		ClassMeta<Cart> classMeta = metaFactory.getClassMeta(CART_CLASSNAME);
		Assert.isTrue(classMeta.getField(CartBase.CUSTOMERID).getName().equals(CartBase.CUSTOMERID));
	}
}
