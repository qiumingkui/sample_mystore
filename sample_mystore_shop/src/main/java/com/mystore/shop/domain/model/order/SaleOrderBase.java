package com.mystore.shop.domain.model.order;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
public class SaleOrderBase {

	@Id
	private SaleOrderId saleOrderId;

	private String username;

	private Date orderDate;

	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name = "sale_order_item", joinColumns = @JoinColumn(name = "sale_order_id"))
	private Collection<SaleOrderItem> saleOrderItems = new HashSet<SaleOrderItem>();

	@Version
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
