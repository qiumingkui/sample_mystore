package com.mystore.common;

public class User extends Person{

	private UserId userId = null;

	private String name;

	protected User() {
		super();
	}

	public UserId getUserId() {
		return userId;
	}

	public void setUserId(UserId userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
