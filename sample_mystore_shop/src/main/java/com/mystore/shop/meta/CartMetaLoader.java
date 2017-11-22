package com.mystore.shop.meta;

import com.mystore.common.meta.ClassMeta;
import com.mystore.common.meta.MetaLoader;
import com.mystore.common.meta.RegisterCenter;
import com.mystore.common.meta.domain.AggregateRootMeta;
import com.mystore.shop.domain.model.cart.Cart;
import com.mystore.shop.domain.model.cart.CartBase;

public class CartMetaLoader implements MetaLoader<Cart> {

	@Override
	public void loading(RegisterCenter registerCenter) {
		registerCenter.getAggregateRootMetaRegister()
				.register(new AggregateRootMeta<Cart>(Cart.class.getName(), CartBase.CARTID));

		registerCenter.getClassMetaRegister().register(new ClassMeta<Cart>(Cart.class));

		registerCenter.getTableRegister().register(new CartTable());
	}

}
