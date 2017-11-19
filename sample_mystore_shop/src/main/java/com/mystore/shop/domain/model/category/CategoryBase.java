package com.mystore.shop.domain.model.category;

import java.io.Serializable;

public class CategoryBase implements Serializable {

	private static final long serialVersionUID = 1L;

	public static CategoryValidator validator = new CategoryValidator();

	public static final String CATEGORYID = "categoryId";
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";

	protected CategoryId categoryId;

	protected String name;

	protected String description;

	protected CategoryBase(CategoryId categoryId, String name, String description) {
		super();
		this.categoryId = categoryId;
		this.name = name;
		this.description = description;
	}

	protected CategoryBase() {
		super();
	}

	protected CategoryId getCategoryId() {
		return categoryId;
	}

	protected void setCategoryId(CategoryId categoryId) {
		this.categoryId = categoryId;
	}

	protected String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}

	protected String getDescription() {
		return description;
	}

	protected void setDescription(String description) {
		this.description = description;
	}

}
