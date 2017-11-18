package com.mystore.common.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConstructorUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.junit.Test;
import org.springframework.util.Assert;

public class BeanUtilsTest {

	private static final long ID = 1;

	@Test
	public void test() {

		User user = null;

		try {

			// Constructor<User> constructor=
			// ConstructorUtils.getAccessibleConstructor(User.class, null);
			//
			// user = ConstructorUtils.invokeConstructor(User.class, null);

			Constructor<User> constructor = User.class.getDeclaredConstructor(null);
			// constructor.setAccessible(true);
			user = constructor.newInstance(null);

			Assert.isTrue(user != null);

			UserId userId = ConstructorUtils.invokeConstructor(UserId.class, ID);

			BeanUtils.setProperty(user, "userId", userId);

			Assert.isTrue(user.getUserId().getId() == ID);

			Assert.isTrue((Long) PropertyUtils.getProperty(userId, "id") == ID);

			BeanUtils.setProperty(user, "userId.id", ID + 1);

			Assert.isTrue((Long) PropertyUtils.getProperty(user, "userId.id") == ID + 1);

		} catch (IllegalAccessException e) {

			e.printStackTrace();

		} catch (InvocationTargetException e) {

			e.printStackTrace();

		} catch (NoSuchMethodException e) {

			e.printStackTrace();

		} catch (InstantiationException e) {

			e.printStackTrace();
		}
	}
}
