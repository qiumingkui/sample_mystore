package com.mystore.shop.domain.model.order;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

public class SaleOrderBase {

	private SaleOrderId saleOrderId;

	private String username;

	private Date orderDate;

	private Collection<SaleOrderItem> saleOrderItems = new ArrayList<SaleOrderItem>();

	private int concurrencyVersion;

	public SaleOrderId getSaleOrderId() {
		return saleOrderId;
	}

	public String getUsername() {
		return username;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public int getConcurrencyVersion() {
		return concurrencyVersion;
	}

	public Collection<SaleOrderItem> getSaleOrderItems() {
		return saleOrderItems;
	}

	public void setSaleOrderId(SaleOrderId saleOrderId) {
		this.saleOrderId = saleOrderId;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public void setConcurrencyVersion(int concurrencyVersion) {
		this.concurrencyVersion = concurrencyVersion;
	}

	public void setSaleOrderItems(Collection<SaleOrderItem> saleOrderItems) {
		this.saleOrderItems = saleOrderItems;
	}

}
