package com.mystore.shop.port.adapter.persistence.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.mystore.common.persistence.Table;
import com.mystore.shop.domain.model.cart.CartId;
import com.mystore.shop.domain.model.cart.CartItem;
import com.mystore.shop.domain.model.productitem.ProductItemId;

public class CartItemTable extends Table<CartItem> {

	public static final String TABLENAME = "cartitem";
	public static final String CARTID = "cartid";
	public static final String PRODUCTITEMID = "productitemid";
	public static final String QUANTITY = "quantity";
	public static final String UNITPRICE = "unitprice";
	public static final String TOTAL = "total";
	@Override
	protected void init() {
		try {
			setName(TABLENAME);

			add(CARTID,
					(PreparedStatement ps, int index, CartItem cartItem) -> ps.setLong(index,
							cartItem.getCartId().getId()),
					(CartItem cartItem, ResultSet rs) -> cartItem.setCartId(new CartId(rs.getLong(CARTID))));
			setForeignKey(CARTID);

			add(PRODUCTITEMID,
					(PreparedStatement ps, int index, CartItem cartItem) -> ps.setLong(index,
							cartItem.getProductItemId().getId()),
					(CartItem cartItem, ResultSet rs) -> cartItem
							.setProductItemId(new ProductItemId(rs.getLong(PRODUCTITEMID))));

			add(QUANTITY,
					(PreparedStatement ps, int index, CartItem cartItem) -> ps.setInt(index, cartItem.getQuantity()),
					(CartItem cartItem, ResultSet rs) -> cartItem.setQuantity(rs.getInt(QUANTITY)));

			add(UNITPRICE,
					(PreparedStatement ps, int index, CartItem cartItem) -> ps.setBigDecimal(index,
							cartItem.getUnitPrice()),
					(CartItem cartItem, ResultSet rs) -> cartItem.setUnitPrice(rs.getBigDecimal(UNITPRICE)));

			add(TOTAL,
					(PreparedStatement ps, int index, CartItem cartItem) -> ps.setBigDecimal(index,
							cartItem.getTotal()),
					(CartItem cartItem, ResultSet rs) -> cartItem.setTotal(rs.getBigDecimal(TOTAL)));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}