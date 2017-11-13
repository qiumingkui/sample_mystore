package com.mystore.shop.domain.model.cart;

import java.math.BigDecimal;
import java.util.Collection;

import org.springframework.util.Assert;

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
	public CartItem cartItem(ProductItemId productItemId) {
		Collection<CartItem> cartItems = getCartItems();
		for (CartItem cartItem : cartItems) {
			if (cartItem.getProductItemId().equals(productItemId)) {
				return cartItem;
			}
		}
		return null;
	}

	@Override
	public BigDecimal total() {
		return getTotal();
	}

	@Override
	public void addCartItem(CartItem cartItem) {
		Assert.isTrue(cartItem(cartItem.getProductItemId()) == null);

		calculateCartItemTotal(cartItem);
		this.cartItems().add(cartItem);

		calculateCartTotal();
	}

	@Override
	public void removeCartItem(ProductItemId productItemId) {
		CartItem cartItem = cartItem(productItemId);
		getCartItems().remove(cartItem);

		calculateCartTotal();
	}

	@Override
	public void increaseCartItem(ProductItemId productItemId, int increaseNumber) {
		CartItem cartItem = cartItem(productItemId);
		if (cartItem != null) {
			int quantity = cartItem.getQuantity();
			quantity += increaseNumber;
			cartItem.setQuantity(quantity);
		}
		calculateCartTotal();
	}

	@Override
	public void decreaseCartItem(ProductItemId productItemId, int decreaseNumber) {
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
		calculateCartTotal();
	}

	private void calculateCartItemTotal(CartItem cartItem) {
		BigDecimal total = cartItem.getUnitPrice().multiply(new BigDecimal(cartItem.getQuantity()));
		cartItem.setTotal(total);
	}

	private void calculateCartTotal() {
		BigDecimal total = new BigDecimal(0);
		Collection<CartItem> cartItems = this.getCartItems();
		for (CartItem cartItem : cartItems) {

			calculateCartItemTotal(cartItem);

			BigDecimal cartItemTotal = cartItem.getTotal();

			total = total.add(cartItemTotal);
		}
		this.setTotal(total);
	}
}
