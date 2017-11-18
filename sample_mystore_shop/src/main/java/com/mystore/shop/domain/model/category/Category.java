package com.mystore.shop.domain.model.category;

import java.io.Serializable;

public class Category implements Serializable {

	private static final long serialVersionUID = 1L;

	protected CategoryId categoryId;

	protected String name;

	protected String description;

	public static CategoryValidator validator = new CategoryValidator();

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
