package com.mystore.shop.domain.model.category;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

public class CategoryModel extends CategoryBase implements Category {

	private static final long serialVersionUID = 1L;

	public static CategoryValidator validator = new CategoryValidator();

	public CategoryModel() {
		super();
	}

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

	@Override
	public CategoryBase categoryBase() {
		CategoryBase categoryBase = new CategoryBase();
		try {
			BeanUtils.copyProperties(categoryBase, this);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return categoryBase;
	}
}
