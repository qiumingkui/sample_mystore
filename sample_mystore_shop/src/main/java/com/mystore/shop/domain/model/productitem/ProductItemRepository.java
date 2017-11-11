package com.mystore.shop.domain.model.productitem;

import java.util.List;

import com.mystore.shop.domain.model.product.Product;

public interface ProductItemRepository {
	public void create(ProductItem productItem);

	public void update(ProductItem productItem);

	public void delete(ProductItemId productItemId);
	
	public Product get(ProductItemId productItemId) throws Exception ;

	public List<ProductItem> getProductItemList() throws Exception;

}
