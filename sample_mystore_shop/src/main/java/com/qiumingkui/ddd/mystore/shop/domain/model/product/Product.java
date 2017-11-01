package com.qiumingkui.ddd.mystore.shop.domain.model.product;

import com.qiumingkui.ddd.mystore.shop.domain.model.category.CategoryId;
import com.qiumingkui.sample.mystore.common.domain.annotation.DomainEntity;

@DomainEntity
public class Product{
	
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

	protected void setProductId(ProductId productId) {
		this._productId = productId;
	}

	protected void setCategoryId(CategoryId categoryId) {
		this._categoryId = categoryId;
	}

	protected void setName(String name) {
		this._name = name;
	}

	protected void setDescription(String description) {
		this._description = description;
	}

}
