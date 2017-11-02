package com.mystore.shop.domain.model.order;

import java.util.Date;

public class SaleOrderFactory {

	public SaleOrder saleOrder(int orderId, String username, Date orderDate) {
		return saleOrderImpl(orderId, username, orderDate);
	}

	protected SaleOrder saleOrderImpl(int orderId, String username, Date orderDate) {
		return new SaleOrderImpl(orderId, username, orderDate);
	}

}
