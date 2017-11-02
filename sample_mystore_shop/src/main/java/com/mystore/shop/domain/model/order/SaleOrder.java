package com.mystore.shop.domain.model.order;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class SaleOrder implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long _orderId;
	
	private String _username;
	
	private Date _orderDate;
	
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

	protected SaleOrder() {
		super();
	}

	protected SaleOrder(String username, Date orderDate) {
		this(null,username,orderDate);
	}
	
	protected SaleOrder(Long orderId, String username, Date orderDate) {
		super();
		this.setOrderId(orderId);
		this.setUsername(username);
		this.setOrderDate(orderDate);
	}
	
	public Long orderId() {
		return this._orderId;
	}

	public String username() {
		return this._username;
	}

	public Date orderDate() {
		return this._orderDate;
	}

	public void changeUsername(String username) {
		this.setUsername(username);
	}

	public void changeOrderDate(Date orderDate) {
		this.setOrderDate(orderDate);
	}

	protected void setOrderId(Long orderId) {
		this._orderId = orderId;
	}

	protected void setUsername(String username) {
		this._username = username;
	}

	protected void setOrderDate(Date orderDate) {
		this._orderDate = orderDate;
	}

}
