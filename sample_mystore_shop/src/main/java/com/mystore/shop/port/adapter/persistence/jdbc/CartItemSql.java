package com.mystore.shop.port.adapter.persistence.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.mystore.common.persistence.jdbc.ValueObjectJdbcDao;
import com.mystore.shop.domain.model.cart.CartId;
import com.mystore.shop.domain.model.cart.CartItem;
import com.mystore.shop.meta.CartItemTable;
import com.mystore.shop.meta.SysMetaFactory;

@Component
public class CartItemSql extends ValueObjectJdbcDao<CartItem, CartId> {

	@Autowired
	@Override
	protected void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	protected void initTable() {
		this.table = new CartItemTable();
	}

	@Override
	protected CartItem produceObject() {
		return new CartItem();
	}

	@Override
	protected CartItem produceObjectBySupId(CartId fk) {
		CartItem cartItem = new CartItem();
		cartItem.setCartId(fk);
		return cartItem;
	}

	@Override
	protected void initClass() {
		this.clazz = CartItem.class;
	}

	@Override
	protected void initMetaFactory() {
		this.metaFactory = SysMetaFactory.instance();
	}
}
