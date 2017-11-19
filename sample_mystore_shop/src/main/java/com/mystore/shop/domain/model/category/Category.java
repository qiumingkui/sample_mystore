package com.mystore.shop.domain.model.category;

import java.io.Serializable;

public class Category extends CategoryBase implements Serializable {

	private static final long serialVersionUID = 1L;

	// public static CategoryValidator validator = new CategoryValidator();
	//
	// public static final String CATEGORYID = "categoryId";
	// public static final String NAME = "name";
	// public static final String DESCRIPTION = "description";
	//
	// protected CategoryId categoryId;
	//
	// protected String name;
	//
	// protected String description;

	protected Category() {
		super();
	}

	protected Category(CategoryId categoryId, String name, String description) {

		super();

		this.setCategoryId(categoryId);

		this.setName(name);

		this.setDescription(description);
	}

	public CategoryId categoryId() {

		return this.getCategoryId();
	}

	public String name() {
		return this.getName();
	}

	public String description() {
		return this.getDescription();
	}

	public void changeName(String name) {
		this.setName(name);
	}

	public void changeDescription(String description) {
		this.setDescription(description);
	}
	
	
}
