package com.mystore.shop.domain.model.category;

public class CategoryModel extends CategoryBase implements Category {

	private static final long serialVersionUID = 1L;

	public static CategoryValidator validator = new CategoryValidator();

	protected CategoryModel(CategoryId categoryId, String name, String description) {

		super();

		this.setCategoryId(categoryId);

		this.setName(name);

		this.setDescription(description);
	}

	@Override
	public CategoryId categoryId() {

		return this.getCategoryId();
	}

	@Override
	public String name() {
		return this.getName();
	}

	@Override
	public String description() {
		return this.getDescription();
	}

	@Override
	public void changeName(String name) {
		this.setName(name);
	}

	@Override
	public void changeDescription(String description) {
		this.setDescription(description);
	}

}
