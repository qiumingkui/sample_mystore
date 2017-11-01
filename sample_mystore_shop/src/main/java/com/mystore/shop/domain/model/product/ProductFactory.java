package com.mystore.shop.domain.model.product;

import org.springframework.stereotype.Component;

import com.mystore.shop.domain.model.category.CategoryId;

@Component
public class ProductFactory {

	public Product product(long productId, long categoryId, String name, String description) {
		ProductId _productId = new ProductId(productId);
		CategoryId _categoryId = new CategoryId(categoryId);
		Product _product = new Product(_productId, _categoryId, name, description);
		return _product;
	}

}
