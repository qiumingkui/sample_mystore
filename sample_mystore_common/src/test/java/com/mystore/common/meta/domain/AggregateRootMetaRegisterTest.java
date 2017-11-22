package com.mystore.common.meta.domain;

import org.junit.Assert;
import org.junit.Test;

import com.mystore.common.User;
import com.mystore.common.UserId;
import com.mystore.common.meta.RegisterCenterFactory;
import com.mystore.common.utils.BeanStringUtil;

public class AggregateRootMetaRegisterTest {
	final String NAME = "name";
	final String USER_NAME = User.class.getName();
	final String USER_SIMPLENAME = User.class.getSimpleName();
	final String USERID_NAME = UserId.class.getName();
	final String USERID_FIELDNAME = BeanStringUtil.convertClassNameToFieldName(UserId.class.getSimpleName());

	final RegisterCenterFactory registerCenterFactory = new RegisterCenterFactory() {
		@Override
		protected void init() {
			registerCenter.getAggregateRootMetaRegister()
					.register(new UserAggregateRootMeta(User.class, USERID_FIELDNAME));
		}
	};

	@Test
	public void register01() {
		UserAggregateRootMeta userAggregateRootMeta = (UserAggregateRootMeta) registerCenterFactory.getRegisterCenter()
				.getAggregateRootMetaRegister().getAggregateRootMeta(USER_NAME);

		Assert.assertTrue(userAggregateRootMeta.getName().equals(USER_NAME));
		Assert.assertTrue(userAggregateRootMeta.getClazz().getSimpleName().equals("User"));
		Assert.assertTrue(userAggregateRootMeta.getField("name").getName().equals("name"));
		Assert.assertTrue(userAggregateRootMeta.getIdentityObject().getName().equals("userId"));
	}

	@Test
	public void register02() {
		UserAggregateRootMeta userAggregateRootMeta = (UserAggregateRootMeta) registerCenterFactory.getRegisterCenter()
				.getAggregateRootMetaRegister().getAggregateRootMeta(USER_NAME);

		Assert.assertTrue(userAggregateRootMeta.getName().equals(USER_NAME));
		Assert.assertTrue(userAggregateRootMeta.getClazz().getSimpleName().equals(USER_SIMPLENAME));
		Assert.assertTrue(userAggregateRootMeta.getField(NAME).getName().equals(NAME));
		Assert.assertTrue(userAggregateRootMeta.getIdentityObject().getName().equals(USERID_FIELDNAME));
	}
}