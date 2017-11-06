package com.mystore.shop.domain.model.cart;

import java.math.BigDecimal;

import com.mystore.shop.domain.model.productitem.ProductItemId;

public class CartItem {

	private int quantity;

	private BigDecimal unitPrice;

	private BigDecimal total;

	private ProductItemId productItemId;

	protected CartItem() {
		super();
	}

	protected CartItem(int quantity, BigDecimal unitPrice, BigDecimal total, ProductItemId productItemId) {
		super();
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.total = total;
		this.productItemId = productItemId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public ProductItemId getProductItemId() {
		return productItemId;
	}

	public void setProductItemId(ProductItemId productItemId) {
		this.productItemId = productItemId;
	}

}
