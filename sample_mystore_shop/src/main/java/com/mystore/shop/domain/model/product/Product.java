package com.mystore.shop.domain.model.product;

import com.mystore.shop.domain.model.category.CategoryId;

public class Product {
	private static ProductValidator validator = new ProductValidator();
	private ProductId _productId;
	private CategoryId _categoryId;
	private String _name;
	private String _description;

	public Product(ProductId productId, CategoryId categoryId, String name, String description) {
		super();
		setProductId(productId);
		setCategoryId(categoryId);
		setName(name);
		setDescription(description);
	}

	public ProductId productId() {
		return _productId;
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

	protected void setProductId(ProductId productId) {
		this._productId = productId;
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
