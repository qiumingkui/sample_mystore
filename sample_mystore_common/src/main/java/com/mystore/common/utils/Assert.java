package com.mystore.common.utils;

public class Assert {

	private static AssertionConcern assertionConcern = new AssertionConcern();

	public static void assertArgumentEquals(Object anObject1, Object anObject2, String aMessage) {
		assertionConcern.assertArgumentEquals(anObject1, anObject2, aMessage);
	}

	public static void assertArgumentFalse(boolean aBoolean, String aMessage) {
		assertionConcern.assertArgumentFalse(aBoolean, aMessage);
	}

	public static void assertArgumentLength(String aString, int aMaximum, String aMessage) {
		assertionConcern.assertArgumentLength(aString, aMaximum, aMessage);
	}

	public static void assertArgumentLength(String aString, int aMinimum, int aMaximum, String aMessage) {
		assertionConcern.assertArgumentLength(aString, aMinimum, aMaximum, aMessage);
	}

	public static void assertArgumentNotEmpty(String aString, String aMessage) {
		assertionConcern.assertArgumentNotEmpty(aString, aMessage);
	}

	public static void assertArgumentNotEquals(Object anObject1, Object anObject2, String aMessage) {
		assertionConcern.assertArgumentNotEquals(anObject1, anObject2, aMessage);
	}

	public static void assertArgumentNotNull(Object anObject, String aMessage) {
		assertionConcern.assertArgumentNotNull(anObject, aMessage);
	}

	public static void assertArgumentRange(double aValue, double aMinimum, double aMaximum, String aMessage) {
		assertionConcern.assertArgumentRange(aValue, aMinimum, aMaximum, aMessage);
	}

	public static void assertArgumentRange(float aValue, float aMinimum, float aMaximum, String aMessage) {
		assertionConcern.assertArgumentRange(aValue, aMinimum, aMaximum, aMessage);
	}

	public static void assertArgumentRange(int aValue, int aMinimum, int aMaximum, String aMessage) {
		assertionConcern.assertArgumentRange(aValue, aMinimum, aMaximum, aMessage);
	}

	public static void assertArgumentRange(long aValue, long aMinimum, long aMaximum, String aMessage) {
		assertionConcern.assertArgumentRange(aValue, aMinimum, aMaximum, aMessage);
	}

	public static void assertArgumentTrue(boolean aBoolean, String aMessage) {
		assertionConcern.assertArgumentTrue(aBoolean, aMessage);
	}

	public static void assertStateFalse(boolean aBoolean, String aMessage) {
		assertionConcern.assertStateFalse(aBoolean, aMessage);
	}

	public static void assertStateTrue(boolean aBoolean, String aMessage) {
		assertionConcern.assertStateTrue(aBoolean, aMessage);
	}
}
