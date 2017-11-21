package com.mystore.common.meta.domain;

import org.junit.Assert;
import org.junit.Test;

import com.mystore.common.User;
import com.mystore.common.UserId;
import com.mystore.common.meta.RegisterCenter;
import com.mystore.common.utils.BeanStringUtil;

public class EntityMetaRegisterTest {
	final String NAME = "name";
	final String USER_NAME = User.class.getName();
	final String USER_SIMPLENAME = User.class.getSimpleName();
	final String USERID_NAME = UserId.class.getName();
	final String USERID_FIELDNAME = BeanStringUtil.convertClassNameToFieldName(UserId.class.getSimpleName());

	@Test
	public void register01() {
		RegisterCenter registerCenter = RegisterCenter.newInstance();
		EntityMetaRegister classMetaRegister = registerCenter.getEntityMetaRegister();

		EntityMeta<User> userMeta = new EntityMeta<User>(User.class, "userId");
		classMetaRegister.register(userMeta);

		Assert.assertTrue(classMetaRegister.getEntityMeta(USER_NAME).getName().equals(USER_NAME));
		Assert.assertTrue(classMetaRegister.getEntityMeta(USER_NAME).getClazz().getSimpleName().equals("User"));
		Assert.assertTrue(classMetaRegister.getEntityMeta(USER_NAME).getField("name").getName().equals("name"));
		Assert.assertTrue(classMetaRegister.getEntityMeta(USER_NAME).getIdentityObject().getName().equals("userId"));
	}

	@Test
	public void register02() {
		RegisterCenter registerCenter = RegisterCenter.newInstance();
		EntityMetaRegister classMetaRegister = registerCenter.getEntityMetaRegister();

		EntityMeta<User> userMeta = new EntityMeta<User>(User.class, USERID_FIELDNAME);
		// FieldMeta<User> userIdMeta = new FieldMeta<User>(
		// SimpleBeanUtil.getFieldWithSupper(User.class, USERID_FIELDNAME));
		// userMeta.putFieldMeta(userIdMeta);
		classMetaRegister.register(userMeta);

		Assert.assertTrue(classMetaRegister.getEntityMeta(USER_NAME).getName().equals(USER_NAME));
		Assert.assertTrue(
				classMetaRegister.getEntityMeta(USER_NAME).getClazz().getSimpleName().equals(USER_SIMPLENAME));
		Assert.assertTrue(classMetaRegister.getEntityMeta(USER_NAME).getField(NAME).getName().equals(NAME));
		Assert.assertTrue(
				classMetaRegister.getEntityMeta(USER_NAME).getIdentityObject().getName().equals(USERID_FIELDNAME));
	}
}
