package com.mystore.shop.port.adapter.persistence.jdbc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.mystore.common.persistence.Column;
import com.mystore.common.persistence.jdbc.JdbcEntityDao;
import com.mystore.shop.domain.model.cart.Cart;
import com.mystore.shop.domain.model.cart.CartId;
import com.mystore.shop.domain.model.customer.CustomerId;

@Component
public class CartSql extends JdbcEntityDao<Cart, CartId> {

	public List<Cart> findAllByCustomerId(CustomerId customerId) {
		List<CartId> cartIds = findAllIdByCustomerId(customerId);
		List<Cart> carts = findAll(cartIds);
		return carts;
	}

	public List<CartId> findAllIdByCustomerId(CustomerId customerId) {
		Collection<Column<Cart>> rsColumns = new ArrayList<Column<Cart>>();
		rsColumns.add(table.primaryKey());

		String SQL = "SELECT #{pk} FROM #{table} WHERE #{customerid}=?";
		SQL = sqlSetting(SQL, "table", table.name());
		SQL = sqlSetting(SQL, "pk", table.primaryKey().getColumnName());
		SQL = sqlSetting(SQL, "customerid", table.column(CartTable.CUSTOMERID).getColumnName());

		List<Cart> objectWithKeyList = jdbcTemplate.query(SQL, new Object[] { customerId.getId() },
				provideRowMapper(rsColumns));

		return fetchIdList(objectWithKeyList);
	}

	@Autowired
	@Override
	protected void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	protected void initTable() {
		this.table = new CartTable();
	}

//	@Override
//	protected Cart produceObject(CartId key) {
//		Cart cart = produceObject();
//		cart.setCartId(key);
//		return cart;
//	}
//
//	@Override
//	protected Cart produceObject() {
//		Cart cart = new Cart();
//		return cart;
//	}
//
//	@Override
//	protected CartId fetchId(Cart object) {
//		CartId cartId = object.getCartId();
//		return cartId;
//	}

}
