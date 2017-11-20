package com.mystore.common.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class SimpleBeanUtil {

	public static Field getFieldWithSupper(Class clazz, String fieldName) {

		Field field = null;
		if (clazz == null) {
			return null;
		}
		try {
			field = clazz.getDeclaredField(fieldName);
		} catch (NoSuchFieldException e) {
			// e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		if ((field == null) && (clazz.getSuperclass() != null)) {
			field = getFieldWithSupper(clazz.getSuperclass(), fieldName);
		}
		return field;
	}

	public static Object newInstance(Class clazz) {

		try {
			Constructor declaredConstructor = clazz.getDeclaredConstructor(null);
			declaredConstructor.setAccessible(true);
			Object obj = declaredConstructor.newInstance(null);
			return obj;
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return null;
	}
}
