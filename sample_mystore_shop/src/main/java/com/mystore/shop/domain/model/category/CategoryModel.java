package com.mystore.shop.domain.model.category;

public class CategoryModel extends CategoryBase implements Category{

	public static CategoryValidator validator = new CategoryValidator();

	public CategoryModel() {
		super();
	}	

	public CategoryModel(CategoryId categoryId, String name, String description) {
		super(categoryId, name, description);
	}

	@Override
	public CategoryId categoryId() {
		return getCategoryId();
	}

	@Override
	public String name() {
		return getName();
	}

	@Override
	public String description() {
		return getDescription();
	}

	@Override
	public void changeName(String name) {
		setName(name);
	}

	@Override
	public void changeDescription(String description) {
		setDescription(description);
	}

}
