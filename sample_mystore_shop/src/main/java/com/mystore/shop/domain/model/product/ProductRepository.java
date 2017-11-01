package com.mystore.shop.domain.model.product;

import com.mystore.shop.domain.model.product.Product;
import com.mystore.shop.domain.model.product.ProductId;

public interface ProductRepository {

	public Product get(ProductId productId);

	public void create(Product product);

	public void update(Product product);

	public void delete(ProductId productId);
}
