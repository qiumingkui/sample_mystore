package com.mystore.common.meta.domain;

import org.junit.Assert;
import org.junit.Test;

import com.mystore.common.User;
import com.mystore.common.UserId;
import com.mystore.common.meta.MetaFactory;
import com.mystore.common.utils.BeanStringUtil;

public class AggregateRootMetaRegisterTest {
	final String NAME = "name";
	final String USER_CLASS_NAME = User.class.getName();
	final String USER_CLASSS_IMPLENAME = User.class.getSimpleName();
	final String USERID_CLASS_NAME = UserId.class.getName();
	final String USERID_FIELD_NAME = BeanStringUtil.convertClassNameToFieldName(UserId.class.getSimpleName());

	final MetaFactory registerCenterFactory = new MetaFactory() {
		@Override
		protected void init() {
			registerCenter.getAggregateRootMetaRegister()
					.register(new UserAggregateRootMeta(USER_CLASS_NAME, USERID_FIELD_NAME));
			registerCenter.getClassMetaRegister().register(new UserClassMeta(User.class));
		}
	};

	@Test
	public void checkRegister() {
		UserAggregateRootMeta userAggregateRootMeta = (UserAggregateRootMeta) registerCenterFactory
				.getAggregateRootMeta(USER_CLASS_NAME);
		UserClassMeta userClassMeta = (UserClassMeta) registerCenterFactory.getClassMeta(USER_CLASS_NAME);

		Assert.assertTrue(userClassMeta.getName().equals(USER_CLASS_NAME));
		Assert.assertTrue(userClassMeta.getClazz().getSimpleName().equals(USER_CLASSS_IMPLENAME));
		Assert.assertTrue(userClassMeta.getField(NAME).getName().equals(NAME));
		Assert.assertTrue(userClassMeta.getField(userAggregateRootMeta.getIdentityObjectFieldName()).getName()
				.equals(USERID_FIELD_NAME));

	}
}