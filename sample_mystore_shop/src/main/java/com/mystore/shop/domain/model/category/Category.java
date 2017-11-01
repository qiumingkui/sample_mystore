package com.mystore.shop.domain.model.category;

public class Category {

	public static CategoryValidator validator = new CategoryValidator();

	private CategoryId _categoryId;

	private String _name;

	private String _description;

	public Category(CategoryId categoryId, String name, String description) {
		super();

		setCategoryId(categoryId);

		setName(name);

		setDescription(description);
	}

	public CategoryId categoryId() {
		return _categoryId;
	}

	public String name() {
		return _name;
	}

	public String description() {
		return _description;
	}

	public void changeName(String name) {
		setName(name);
	}

	public void changeDescription(String description) {
		setDescription(description);
	}

	protected void setCategoryId(CategoryId categoryId) {
		this._categoryId = categoryId;
	}

	protected void setName(String name) {
		validator.assertName(name);
		this._name = name;
	}

	protected void setDescription(String description) {
		validator.assertDescription(description);
		this._description = description;
	}

}
