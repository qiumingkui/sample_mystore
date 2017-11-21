package com.mystore.common.util;

import java.lang.reflect.Field;

import org.junit.Test;
import org.springframework.util.Assert;

import com.mystore.common.User;
import com.mystore.common.utils.SimpleBeanUtil;

public class SimpleBeanUitlTest {

	@Test
	public void test() throws NoSuchFieldException, SecurityException {
		Field field = SimpleBeanUtil.getFieldWithSupper(User.class, "age");
		Assert.isTrue(field != null);
	}
}
