package com.mystore.shop.port.adapter.persistence.jdbc;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mystore.shop.domain.model.cart.Cart;
import com.mystore.shop.domain.model.cart.CartBase;
import com.mystore.shop.domain.model.cart.CartFactory;
import com.mystore.shop.domain.model.cart.CartId;
import com.mystore.shop.domain.model.cart.CartItem;
import com.mystore.shop.domain.model.cart.CartModel;
import com.mystore.shop.domain.model.cart.CartRepository;
import com.mystore.shop.domain.model.customer.CustomerId;

@Component
public class CartRepositorySql implements CartRepository {

	@Autowired
	private CartFactory cartFactory;

	@Autowired
	private CartBaseSql cartBaseSql;

	@Autowired
	private CartItemSql cartItemSql;

	@Override
	public Cart get(CartId cartId) {

		CartBase cartBase = cartBaseSql.findOneById(cartId);
		if (cartBase == null)
			return null;

		List<CartItem> cartItems = cartItemSql.findAllByFK(cartId);
		cartBase.setCartItems(cartItems);

		return cartFactory.cart(cartBase);
	}

	@Override
	public Cart getByCustomerId(CustomerId customerId) {

		List<CartId> list = cartBaseSql.findAllIdByCustomerId(customerId);

		if (list.size() > 0 && list.get(0) != null)
			return get(list.get(0));

		return null;
	}

	@Override
	public void create(Cart cart) {

		cartBaseSql.insert((CartModel) cart);

		Collection<CartItem> cartItems = cart.cartItems();
		for (CartItem cartItem : cartItems) {
			cartItemSql.insert(cartItem);
		}
	}

	@Override
	public void update(Cart cart) {

		cartBaseSql.update((CartModel) cart);

		cartItemSql.deleteByFK(cart.cartId());

		Collection<CartItem> cartItems = cart.cartItems();
		for (CartItem cartItem : cartItems) {
			cartItemSql.insert(cartItem);
		}
	}

	@Override
	public void delete(CartId cartId) {

		cartBaseSql.deleteById(cartId);

		cartItemSql.deleteByFK(cartId);
	}

	@Override
	public List<Cart> getList() {

		List<CartBase> cartBaseList = cartBaseSql.findAll();

		return cartFactory.cartList(cartBaseList);
	}

}
