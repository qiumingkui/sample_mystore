package com.mystore.shop.domain.model.product;

import java.util.List;

import com.mystore.shop.domain.model.product.Product;
import com.mystore.shop.domain.model.product.ProductId;

public interface ProductRepository {

	public void create(Product product);

	public void update(Product product);

	public void delete(ProductId productId);
	
	public Product get(ProductId productId) throws Exception ;

	public List<Product> getProductList() throws Exception;

}
