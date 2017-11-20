package com.mystore.shop.port.adapter.persistence.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.mystore.common.persistence.Table;
import com.mystore.shop.domain.model.cart.Cart;
import com.mystore.shop.domain.model.cart.CartId;
import com.mystore.shop.domain.model.customer.CustomerId;

public class CartTable extends Table<Cart> {

	public static final String TABLENAME = "cart";
	public static final String ID = "id";
	public static final String CUSTOMERID = "customerid";
	public static final String TOTAL = "total";

	@Override
	protected void init() {
		try {
			setClazz(Cart.class);
			setTableName(TABLENAME);

			add(ID, Cart.CARTID + "." + CartId.ID);
			setPrimaryKay(ID);

			add(CUSTOMERID, Cart.CUSTOMERID + "." + CustomerId.ID);

			add(TOTAL, Cart.TOTAL);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// @Override
	// protected void init() {
	// try {
	// setTableName(TABLENAME);
	//
	// add(ID, (PreparedStatement ps, int index, Cart cart) ->
	// ps.setLong(index,
	// cart.getCartId().getId()),
	// (Cart cart, ResultSet rs) -> cart.setCartId(new
	// CartId(rs.getLong(ID))));
	// setPrimaryKay(ID);
	//
	//
	// add(CUSTOMERID, (PreparedStatement ps, int index, Cart cart) ->
	// ps.setLong(index,
	// cart.getCustomerId().getId()), (Cart cart, ResultSet rs) ->
	// cart.setCustomerId(new CustomerId(rs.getLong(CUSTOMERID))));
	//
	// add(TOTAL,
	// (PreparedStatement ps, int index, Cart cart) ->
	// ps.setBigDecimal(index,
	// cart.getTotal()),
	// (Cart cart, ResultSet rs) -> cart.setTotal(rs.getBigDecimal(TOTAL)));
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
}
