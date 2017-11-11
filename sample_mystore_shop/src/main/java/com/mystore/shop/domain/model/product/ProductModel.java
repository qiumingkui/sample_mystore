package com.mystore.shop.domain.model.product;

import com.mystore.shop.domain.model.category.CategoryId;

public class ProductModel extends ProductBase implements Product{

	private static final long serialVersionUID = 1L;
	
	public ProductModel() {
		super();
	}


	public ProductModel(ProductId productId, CategoryId categoryId, String name, String description) {
		super();
		setProductId(productId);
		setCategoryId(categoryId);
		setName(name);
		setDescription(description);
	}


	public ProductId productId() {
		return getProductId();
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
