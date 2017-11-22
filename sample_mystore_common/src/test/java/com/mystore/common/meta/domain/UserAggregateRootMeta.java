package com.mystore.common.meta.domain;

import com.mystore.common.User;

public class UserAggregateRootMeta extends AggregateRootMeta<User> {

	public UserAggregateRootMeta(String className, String identityObjectFieldName) {
		super(className, identityObjectFieldName);
	}

}
