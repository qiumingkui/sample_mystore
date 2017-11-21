package com.mystore.shop.domain.model.cart;

import java.math.BigDecimal;

import com.mystore.shop.domain.model.productitem.ProductItemId;

public class CartItem {
	
	public static final String CARTID="cartId";
	public static final String PRODUCTITEMID="productItemId";
	public static final String QUANTITY="quantity";
	public static final String UNITPRICE="unitPrice";
	public static final String TOTAL="total";

	private CartId cartId;

	private ProductItemId productItemId;

	private int quantity;

	private BigDecimal unitPrice;

	private BigDecimal total;

	public CartItem() {
		super();
	}

	public CartItem(CartId cartId, ProductItemId productItemId, int quantity, BigDecimal unitPrice, BigDecimal total) {
		super();
		this.cartId = cartId;
		this.productItemId = productItemId;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.total = total;
	}

	public CartId getCartId() {
		return cartId;
	}

	public void setCartId(CartId cartId) {
		this.cartId = cartId;
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
