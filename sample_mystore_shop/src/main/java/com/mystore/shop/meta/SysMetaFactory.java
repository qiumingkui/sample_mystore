package com.mystore.shop.meta;

import org.springframework.stereotype.Component;

import com.mystore.common.meta.MetaFactory;

@Component
public class SysMetaFactory extends MetaFactory {

	@Override
	protected void init() {
		new CategoryMetaLoader().loading(registerCenter);
	}

}
