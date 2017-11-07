package com.mystore.shop.domain.model.category;

public class CategoryModel extends CategoryBase implements Category{

	public static CategoryValidator validator = new CategoryValidator();

	public CategoryModel() {
		super();
	}	

	public CategoryModel(CategoryId categoryId, String name, String description) {
		super(categoryId, name, description);
	}

	public CategoryId categoryId() {
		return getCategoryId();
	}

	public String name() {
		return getName();
	}

	public String description() {
		return getDescription();
	}

	public void changeName(String name) {
		setName(name);
	}

	public void changeDescription(String description) {
		setDescription(description);
	}

}
