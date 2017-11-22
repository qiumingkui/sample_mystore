package com.mystore.shop.meta;

import com.mystore.common.meta.ClassMeta;
import com.mystore.common.meta.MetaLoader;
import com.mystore.common.meta.RegisterCenter;
import com.mystore.common.meta.domain.ValueObjectMeta;
import com.mystore.shop.domain.model.cart.CartItem;

public class CartItemMetaLoader implements MetaLoader<CartItem> {

	@Override
	public void loading(RegisterCenter registerCenter) {

		registerCenter.getValueObjectMetaRegister()
				.register(new ValueObjectMeta<>(CartItem.class.getName(), CartItem.CARTID));

		registerCenter.getClassMetaRegister().register(new ClassMeta<CartItem>(CartItem.class));

		registerCenter.getTableRegister().register(new CartItemTable());
	}

}
