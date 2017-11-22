package com.mystore.common.meta.domain;

import com.mystore.common.User;

public class UserAggregateRootMeta extends AggregateRootMeta<User> {

	public UserAggregateRootMeta(Class<User> clazz, String identityObjectName) {
		super(clazz, identityObjectName);
	}

}
