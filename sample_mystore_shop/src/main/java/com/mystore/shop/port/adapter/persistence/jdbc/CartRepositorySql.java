package com.mystore.shop.port.adapter.persistence.jdbc;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mystore.shop.domain.model.cart.Cart;
import com.mystore.shop.domain.model.cart.CartId;
import com.mystore.shop.domain.model.cart.CartItem;
import com.mystore.shop.domain.model.cart.CartRepository;
import com.mystore.shop.domain.model.customer.CustomerId;

@Component
public class CartRepositorySql implements CartRepository {

	@Autowired
	private CartSql cartSql;

	@Autowired
	private CartItemSql cartItemSql;

	@Override
	public Cart get(CartId cartId) {

		Cart cart = cartSql.findOneById(cartId);
		if (cart == null)
			return null;

		List<CartItem> cartItems = cartItemSql.findAllBySupId(cartId);
		cart.setCartItems(cartItems);

		return cart;
	}

	@Override
	public Cart getByCustomerId(CustomerId customerId) {

		List<CartId> list = cartSql.findAllIdByCustomerId(customerId);

		if (list.size() > 0 && list.get(0) != null)
			return get(list.get(0));

		return null;
	}

	@Override
	public void create(Cart cart) {

		cartSql.insert(cart);
		Collection<CartItem> cartItems = cart.cartItems();
		for (CartItem cartItem : cartItems) {
			cartItemSql.insert(cartItem);
		}
	}

	@Override
	public void update(Cart cart) {

		cartSql.update(cart);
		cartItemSql.deleteBySupId(cart.cartId());
		Collection<CartItem> cartItems = cart.cartItems();
		for (CartItem cartItem : cartItems) {
			cartItemSql.insert(cartItem);
		}
	}

	@Override
	public void delete(CartId cartId) {

		cartSql.deleteById(cartId);
		cartItemSql.deleteBySupId(cartId);
	}

	@Override
	public List<Cart> getList() {

		List<Cart> cartList = cartSql.findAll();
		return cartList;
	}

}
