package com.mystore.shop.port.adapter.persistence.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.mystore.common.persistence.jdbc.JdbcValueObjectDao;
import com.mystore.shop.domain.model.cart.CartId;
import com.mystore.shop.domain.model.cart.CartItem;

@Component
public class CartItemSql extends JdbcValueObjectDao<CartItem, CartId> {

	@Autowired
	@Override
	protected void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	protected void init() {
		this.table = new CartItemTable();
	}

	@Override
	protected CartItem produceObject() {
		return new CartItem();
	}

	@Override
	protected CartItem produceObject(CartId fk) {
		CartItem cartItem = new CartItem();
		cartItem.setCartId(fk);
		return cartItem;
	}

}
