package com.mystore.shop.meta;

import com.mystore.common.meta.MetaFactory;

public class SysMetaFactory extends MetaFactory {

	private static SysMetaFactory self;
	private static Object lock = new Object();

	public static SysMetaFactory instance() {
		synchronized (lock) {
			if (self == null)
				self = new SysMetaFactory();
		}
		return self;
	}

	@Override
	protected void init() {
		new CategoryMetaLoader().loading(registerCenter);
		new CartMetaLoader().loading(registerCenter);
		new CartItemMetaLoader().loading(registerCenter);
		new SaleOrderMetaLoader().loading(registerCenter);
		new SaleOrderItemMetaLoader().loading(registerCenter);
	}
}
