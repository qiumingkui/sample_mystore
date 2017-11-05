package com.mystore.shop.domain.model.order;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
public class SaleOrderBase {

	@Id
	private SaleOrderId _saleOrderId;

	private String _username;

	private Date _orderDate;

	@Version
	private int concurrencyVersion;

	protected SaleOrderId getSaleOrderId() {
		return _saleOrderId;
	}

	protected String getUsername() {
		return _username;
	}

	protected Date getOrderDate() {
		return _orderDate;
	}

	protected int getConcurrencyVersion() {
		return concurrencyVersion;
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

	protected void setConcurrencyVersion(int concurrencyVersion) {
		this.concurrencyVersion = concurrencyVersion;
	}

}
