package com.mystore.shop.domain.model.productitem;

import java.io.Serializable;
import java.math.BigDecimal;

import com.mystore.shop.domain.model.product.ProductId;

public interface ProductItem extends Serializable{

	public ProductItemId productItemId() ;

	public ProductId productId();

	public BigDecimal listPrice();

	public BigDecimal unitCost() ;

	public int quantity();

	public void changeListPrice(BigDecimal listPrice);
	
	public void changeUnitCost(BigDecimal unitCost);
	
	public void changeQuantity(int quantity);
	
}
