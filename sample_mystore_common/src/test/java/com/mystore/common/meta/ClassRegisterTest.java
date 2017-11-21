package com.mystore.common.meta;

import org.junit.Assert;
import org.junit.Test;

import com.mystore.common.User;

public class ClassRegisterTest {

	@Test
	public void register() {
		RegisterCenter registerCenter = RegisterCenter.newInstance();
		ClassMetaRegister classMetaRegister = registerCenter.getClassMetaRegister();
		ClassMeta<User> userMeta = new ClassMeta<User>(User.class);
		classMetaRegister.register(userMeta);
		Assert.assertTrue(userMeta.getSimpleName().equals(User.class.getSimpleName()));
		Assert.assertTrue(userMeta.getName().equals(User.class.getName()));
	}
}
