package com.mystore.shop.domain.model.order;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class SaleOrderBase {

	public static final String SALEORDERID = "saleOrderId";
	public static final String USERNAME = "username";
	public static final String ORDERDATE = "orderDate";

	private SaleOrderId saleOrderId;

	private String username;

	private Date orderDate;

	private Collection<SaleOrderItem> saleOrderItems = new ArrayList<SaleOrderItem>();

	private int concurrencyVersion;

	protected SaleOrderId getSaleOrderId() {
		return saleOrderId;
	}

	protected String getUsername() {
		return username;
	}

	protected Date getOrderDate() {
		return orderDate;
	}

	protected int getConcurrencyVersion() {
		return concurrencyVersion;
	}

	protected Collection<SaleOrderItem> getSaleOrderItems() {
		return saleOrderItems;
	}

	protected void setSaleOrderId(SaleOrderId saleOrderId) {
		this.saleOrderId = saleOrderId;
	}

	protected void setUsername(String username) {
		this.username = username;
	}

	protected void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	protected void setConcurrencyVersion(int concurrencyVersion) {
		this.concurrencyVersion = concurrencyVersion;
	}

	protected void setSaleOrderItems(Collection<SaleOrderItem> saleOrderItems) {
		this.saleOrderItems = saleOrderItems;
	}

}
