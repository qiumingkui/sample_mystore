package com.mystore.shop.domain.model.cart;

import java.math.BigDecimal;
import java.util.Collection;

import com.mystore.shop.domain.model.customer.CustomerId;
import com.mystore.shop.domain.model.product.ProductItemId;

public interface Cart {

	public CustomerId customerId();

	public CartId cartId();

	public Collection<CartItem> cartItems();

	public BigDecimal total();

	public void increase(ProductItemId productItemId, int increaseNumber);

	public void decrease(ProductItemId productItemId,int decreaseNumber);

	public void remove(ProductItemId productItemId);
}
