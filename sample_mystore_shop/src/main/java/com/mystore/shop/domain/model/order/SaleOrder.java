package com.mystore.shop.domain.model.order;

import java.io.Serializable;
import java.util.Date;

public class SaleOrder extends SaleOrderBase implements Serializable {

	private static final long serialVersionUID = 1L;

	public SaleOrder() {
		super();
	}

	public SaleOrder(SaleOrderId saleOrderId, String username, Date orderDate) {
		super();
		this.setSaleOrderId(saleOrderId);
		this.setUsername(username);
		this.setOrderDate(orderDate);
	}

	
	public SaleOrderId saleOrderId() {
		return getSaleOrderId();
	}

	
	public String username() {
		return getUsername();
	}

	
	public Date orderDate() {
		return getOrderDate();
	}

	
	public void changeUsername(String username) {
		setUsername(username);
	}

	
	public void changeOrderDate(Date orderDate) {
		setOrderDate(orderDate);
	}

	
	public void addSaleOrderItem(SaleOrderItem saleOrderItem) {
		getSaleOrderItems().add(saleOrderItem);
	}

}
