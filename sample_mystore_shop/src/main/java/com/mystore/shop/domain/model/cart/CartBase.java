package com.mystore.shop.domain.model.cart;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import com.mystore.shop.domain.model.customer.CustomerId;

public class CartBase implements Serializable {

	private static final long serialVersionUID = -1;

	public static final String CUSTOMERID="customerId";
	public static final String CARTID="cartId";
	public static final String TOTAL ="total";
	
	private CustomerId customerId;

	private CartId cartId;

	private Collection<CartItem> cartItems = new ArrayList<CartItem>();

	private BigDecimal total;

	public CartBase(CustomerId customerId, CartId cartId, Collection<CartItem> cartItems, BigDecimal total) {
		super();
		this.customerId = customerId;
		this.cartId = cartId;
		this.cartItems = cartItems;
		this.total = total;
	}

	public CartBase() {
		super();
	}

	public CustomerId getCustomerId() {
		return customerId;
	}

	public void setCustomerId(CustomerId customerId) {
		this.customerId = customerId;
	}

	public CartId getCartId() {
		return cartId;
	}

	public void setCartId(CartId cartId) {
		this.cartId = cartId;
	}

	public Collection<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(Collection<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

}
