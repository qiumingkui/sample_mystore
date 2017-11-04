package com.mystore.shop.domain.model.order;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class SaleOrder implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SaleOrderId _saleOrderId;

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

	// protected SaleOrder(String username, Date orderDate) {
	// this(null, username, orderDate);
	// }

	protected SaleOrder(SaleOrderId saleOrderId, String username, Date orderDate) {
		super();
		this.setSaleOrderId(saleOrderId);
		this.setUsername(username);
		this.setOrderDate(orderDate);
	}

	public SaleOrderId saleOrderId() {
		return this._saleOrderId;
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

	protected void setSaleOrderId(SaleOrderId saleOrderId) {
		this._saleOrderId = saleOrderId;
	}

	protected void setUsername(String username) {
		this._username = username;
	}

	protected void setOrderDate(Date orderDate) {
		this._orderDate = orderDate;
	}

}
