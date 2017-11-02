package com.mystore.shop.domain.model.order;

import java.io.Serializable;
import java.util.Date;


public class SaleOrderBase implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int orderId;
	private String username;
	private Date orderDate;
	// private String shipAddress1;
	// private String shipAddress2;
	// private String shipCity;
	// private String shipState;
	// private String shipZip;
	// private String shipCountry;
	// private String billAddress1;
	// private String billAddress2;
	// private String billCity;
	// private String billState;
	// private String billZip;
	// private String billCountry;
	// private String courier;

	public SaleOrderBase() {
		super();
	}

	public SaleOrderBase(int orderId, String username, Date orderDate) {
		super();
		this.orderId = orderId;
		this.username = username;
		this.orderDate = orderDate;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

}
