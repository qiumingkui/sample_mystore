package com.mystore.shop.domain.model.product;

import com.mystore.shop.domain.model.category.CategoryId;

public interface Product {

	public ProductId productId();

	public CategoryId categoryId();

	public String name();

	public String description();

	public void changeName(String name);

	public void changeDescription(String description);

}
