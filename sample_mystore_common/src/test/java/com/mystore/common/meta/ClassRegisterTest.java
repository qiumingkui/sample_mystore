package com.mystore.common.meta;

import org.junit.Assert;
import org.junit.Test;

import com.mystore.common.User;

public class ClassRegisterTest {
	final String USER_NAME = User.class.getName();
	final String USER_SIMPLE_NAME = User.class.getSimpleName();

	@Test
	public void register() {
		RegisterCenter registerCenter = RegisterCenter.instance();
		ClassMetaRegister classMetaRegister = registerCenter.getClassMetaRegister();

		ClassMeta<User> userMeta = new ClassMeta<User>(User.class);
		classMetaRegister.register(userMeta);
		Assert.assertTrue(classMetaRegister.getClassMeta(USER_NAME).getName().equals(USER_NAME));
		Assert.assertTrue(
				classMetaRegister.getClassMeta(USER_NAME).getClazz().getSimpleName().equals(USER_SIMPLE_NAME));
	}
}
