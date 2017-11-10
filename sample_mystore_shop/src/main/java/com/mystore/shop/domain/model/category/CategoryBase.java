package com.mystore.shop.domain.model.category;

import java.io.Serializable;

public class CategoryBase implements Serializable {

	private static final long serialVersionUID = 1L;

	public static CategoryValidator validator = new CategoryValidator();

	private CategoryId categoryId = new CategoryId();

	private String name;

	private String description;

	public CategoryBase() {
		super();
	}

	public CategoryBase(CategoryId categoryId, String name, String description) {
		super();
		this.categoryId = categoryId;
		this.name = name;
		this.description = description;
	}

	public CategoryId getCategoryId() {
		return categoryId;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public void setName(String name) {
		// validator.assertName(name);
		this.name = name;
	}

	public void setCategoryId(CategoryId categoryId) {
		this.categoryId = categoryId;
	}

	public void setDescription(String description) {
		// validator.assertDescription(description);
		this.description = description;
	}

}
