package com.mystore.common.meta;

import org.junit.Assert;
import org.junit.Test;

import com.mystore.common.User;
import com.mystore.common.UserId;
import com.mystore.common.meta.EntityMeta;
import com.mystore.common.utils.BeanStringUtil;

public class EntityMetaTest {
	final String NAME = "name";
	final String USER_NAME = User.class.getName();
	final String USER_SIMPLENAME = User.class.getSimpleName();
	final String USERID_NAME = UserId.class.getName();
	final String USERID_FIELDNAME = BeanStringUtil.convertClassNameToFieldName(UserId.class.getSimpleName());

	@Test
	public void register01() {
		EntityMeta<User> userMeta = new EntityMeta<User>(User.class, "userId");

		Assert.assertTrue(userMeta.getName().equals(USER_NAME));
		Assert.assertTrue(userMeta.getClazz().getSimpleName().equals("User"));
		Assert.assertTrue(userMeta.getField("name").getName().equals("name"));
		Assert.assertTrue(userMeta.getIdentityObject().getName().equals("userId"));
	}

	@Test
	public void register02() {
		EntityMeta<User> userMeta = new EntityMeta<User>(User.class, USERID_FIELDNAME);

		Assert.assertTrue(userMeta.getName().equals(USER_NAME));
		Assert.assertTrue(
				userMeta.getClazz().getSimpleName().equals(USER_SIMPLENAME));
		Assert.assertTrue(userMeta.getField(NAME).getName().equals(NAME));
		Assert.assertTrue(
				userMeta.getIdentityObject().getName().equals(USERID_FIELDNAME));
	}
}