package com.mystore.shop.domain.model.cart;

import java.math.BigDecimal;
import java.util.Collection;

import com.mystore.shop.domain.model.customer.CustomerId;
import com.mystore.shop.domain.model.productitem.ProductItemId;

public interface Cart {

	public CustomerId customerId();

	public CartId cartId();

	public Collection<CartItem> cartItems();

	public CartItem cartItem(ProductItemId productItemId);

	public BigDecimal total();

	public void addCartItem(CartItem cartItem);

	public void removeCartItem(ProductItemId productItemId);

	public void increaseCartItem(ProductItemId productItemId, int increaseNumber);

	public void decreaseCartItem(ProductItemId productItemId,int decreaseNumber);
}
