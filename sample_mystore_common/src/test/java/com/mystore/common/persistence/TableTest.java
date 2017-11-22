package com.mystore.common.persistence;

import java.lang.reflect.InvocationTargetException;

import org.junit.Test;
import org.springframework.util.Assert;

import com.mystore.common.User;
import com.mystore.common.UserId;
import com.mystore.common.persistence.Table;
import com.mystore.common.utils.SimpleBeanUtil;

public class TableTest {

	final long USERID = 1;

	final Table table = new Table() {
		@Override
		protected void init() {

		}
	};

	@Test
	public void getFieldPathValue() {

		try {
			// User user = new User();
			User user = (User) SimpleBeanUtil.newInstance(User.class);
			UserId userId = new UserId(USERID);
			user.setUserId(userId);
			Object object = table.getFieldPathValue(user, "userId.id");
			Assert.isTrue(object != null);
			Assert.isTrue(object.equals(USERID));

			// User user1 = new User();
			User user1 = (User) SimpleBeanUtil.newInstance(User.class);
			Object object1 = table.getFieldPathValue(user1, "userId.id");
			Assert.isTrue(object1 == null);

			// User user2 = new User();
			User user2 = (User) SimpleBeanUtil.newInstance(User.class);
			Object object2 = table.getFieldPathValue(user2, "userId");
			Assert.isTrue(object2 == null);
			user2.setUserId(new UserId(USERID));
			Assert.isTrue(object != null);
			Assert.isTrue(object.equals(USERID));

		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void setFieldPathValue() {

		try {
			// User user = new User();
			User user = (User) SimpleBeanUtil.newInstance(User.class);

			table.setFieldPathValue(user, "userId.id", USERID);
			Assert.isTrue(user.getUserId() != null);
			Assert.isTrue(user.getUserId().getId() == USERID);

			// User user1 = new User();
			User user1 = (User) SimpleBeanUtil.newInstance(User.class);

			user1.setUserId(new UserId(USERID - 1));
			table.setFieldPathValue(user1, "userId.id", USERID);
			Assert.isTrue(user1.getUserId() != null);
			Assert.isTrue(user1.getUserId().getId() == USERID);

			// User user2 = new User();
			User user2 = (User) SimpleBeanUtil.newInstance(User.class);

			table.setFieldPathValue(user2, "userId", new UserId(USERID));
			Assert.isTrue(user2.getUserId() != null);
			Assert.isTrue(user2.getUserId().getId() == USERID);

		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}

}
