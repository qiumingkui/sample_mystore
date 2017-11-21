package com.mystore.shop.port.adapter.persistence.jdbc;

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
			setClazz(CartItem.class);
			setTableName(TABLENAME);

			add(CARTID, CartItem.CARTID + "." + CartId.ID);
			setForeignKey(CARTID);

			add(PRODUCTITEMID, CartItem.PRODUCTITEMID + "." + ProductItemId.ID);

			add(QUANTITY, CartItem.QUANTITY);

			add(UNITPRICE, CartItem.UNITPRICE);

			add(TOTAL, CartItem.TOTAL);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}