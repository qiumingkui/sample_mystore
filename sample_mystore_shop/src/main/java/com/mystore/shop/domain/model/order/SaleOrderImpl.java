package com.mystore.shop.domain.model.order;

import java.util.Date;

public class SaleOrderImpl extends SaleOrderBase implements SaleOrder {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SaleOrderImpl() {
		super();
	}

	public SaleOrderImpl(int orderId, String username, Date orderDate) {
		super(orderId, username, orderDate);
	}

	public int orderId() {
		return this.getOrderId();
	}

	public String username() {
		return this.getUsername();
	}

	public Date orderDate() {
		return this.getOrderDate();
	}

	public void changeUsername(String username) {
		this.setUsername(username);
	}

	public void changeOrderDate(Date orderDate) {
		this.setOrderDate(orderDate);
	}

}
