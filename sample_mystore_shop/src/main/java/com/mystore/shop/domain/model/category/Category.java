package com.mystore.shop.domain.model.category;

import java.io.Serializable;

public class Category extends CategoryBase implements Serializable {

	private static final long serialVersionUID = 1L;

	protected Category() {
		super();
	}

	protected Category(CategoryId categoryId, String name, String description) {

		super(categoryId, name, description);
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
