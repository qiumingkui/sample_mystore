package com.mystore.shop.domain.model.category;

public class CategoryModel implements Category {

	public static CategoryValidator validator = new CategoryValidator();

	private CategoryBase categoryBase;

	protected CategoryModel() {
		super();
	}

	protected CategoryModel(CategoryBase categoryBase) {
		super();
		this.categoryBase = categoryBase;
	}

	protected CategoryModel(CategoryId categoryId, String name, String description) {
		super();
		this.categoryBase = new CategoryBase(categoryId, name, description);
	}

	@Override
	public CategoryId categoryId() {
		return categoryBase.getCategoryId();
	}

	@Override
	public String name() {
		return categoryBase.getName();
	}

	@Override
	public String description() {
		return categoryBase.getDescription();
	}

	@Override
	public void changeName(String name) {
		categoryBase.setName(name);
	}

	@Override
	public void changeDescription(String description) {
		categoryBase.setDescription(description);
	}

	@Override
	public CategoryBase categoryBase() {
		return categoryBase;
	}

}
