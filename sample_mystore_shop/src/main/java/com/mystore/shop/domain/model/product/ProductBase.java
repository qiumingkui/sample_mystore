package com.mystore.shop.domain.model.product;

import java.io.Serializable;

import com.mystore.shop.domain.model.category.CategoryId;

public class ProductBase implements Serializable{

	private static final long serialVersionUID = 1L;
	private static ProductValidator validator = new ProductValidator();
	private ProductId productId;
	private CategoryId categoryId;
	private String name;
	private String description;

	public ProductBase(ProductId productId, CategoryId categoryId, String name, String description) {
		super();
		this.setProductId(productId);
		this.setCategoryId(categoryId);
		this.setName(name);
		this.setDescription(description);
	}

	public ProductBase() {
		super();
	}

	public ProductId getProductId() {
		return productId;
	}

	public void setProductId(ProductId productId) {
		this.productId = productId;
	}

	public CategoryId getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(CategoryId categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		validator.assertName(name);
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		validator.assertDescription(description);
		this.description = description;
	}

}
