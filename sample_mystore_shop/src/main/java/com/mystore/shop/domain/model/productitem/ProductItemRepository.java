package com.mystore.shop.domain.model.productitem;

import java.util.List;

import com.mystore.shop.domain.model.product.ProductId;

public interface ProductItemRepository {
	public void create(ProductItem productItem);

	public void update(ProductItem productItem);

	public void delete(ProductItemId productItemId);
	
	public ProductItem get(ProductItemId productItemId) throws Exception ;

	public List<ProductItem> getItemList() throws Exception;

	public List<ProductItem> getListByProductId(ProductId productId) throws Exception;

}
