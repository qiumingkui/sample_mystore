package com.mystore.shop.domain.model.cart;

import java.math.BigDecimal;
import java.util.Collection;

import com.mystore.shop.domain.model.customer.CustomerId;
import com.mystore.shop.domain.model.productitem.ProductItemId;

public class CartModel extends CartBase implements Cart {

	private static final long serialVersionUID = 1L;

	protected CartModel() {
		super();
	}

	protected CartModel(CustomerId customerId, CartId cartId, Collection<CartItem> cartItems, BigDecimal total) {
		super(customerId, cartId, cartItems, total);
	}

	@Override
	public CustomerId customerId() {
		return getCustomerId();
	}

	@Override
	public CartId cartId() {
		return getCartId();
	}

	@Override
	public Collection<CartItem> cartItems() {
		return getCartItems();
	}

	@Override
	public BigDecimal total() {
		return getTotal();
	}

	@Override
	public void increase(ProductItemId productItemId, int increaseNumber) {
		CartItem cartItem = cartItem(productItemId);
		if (cartItem != null) {
			int quantity = cartItem.getQuantity();
			quantity += increaseNumber;
			cartItem.setQuantity(quantity);
		}
	}

	@Override
	public void decrease(ProductItemId productItemId, int decreaseNumber) {
		CartItem cartItem = cartItem(productItemId);
		if (cartItem != null) {
			int quantity = cartItem.getQuantity();
			if (quantity <= decreaseNumber) {
				quantity = 0;
			} else {
				quantity -= decreaseNumber;
			}
			cartItem.setQuantity(quantity);
		}

	}

	@Override
	public void remove(ProductItemId productItemId) {
		CartItem cartItem = cartItem(productItemId);
		getCartItems().remove(cartItem);
	}

	private CartItem cartItem(ProductItemId productItemId) {
		Collection<CartItem> cartItems = getCartItems();
		for (CartItem cartItem : cartItems) {
			if (cartItem.getProductItemId().equals(productItemId)) {
				return cartItem;
			}
			;
		}
		return null;
	}
}
