package com.mystore.common.meta;

import org.junit.Assert;
import org.junit.Test;

import com.mystore.common.User;

public class ClassMetaTest {
	final String USER_NAME = User.class.getName();
	final String USER_SIMPLE_NAME = User.class.getSimpleName();

	@Test
	public void register() {
		ClassMeta<User> userMeta = new ClassMeta<User>(User.class);
		Assert.assertTrue(userMeta.getName().equals(USER_NAME));
		Assert.assertTrue(userMeta.getClazz().getSimpleName().equals(USER_SIMPLE_NAME));
	}
}
