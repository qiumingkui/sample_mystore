package com.mystore.shop.domain.model.order;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "sale_order")
public class SaleOrderModel extends SaleOrderBase implements SaleOrder {

	private static final long serialVersionUID = 1L;

	public SaleOrderModel() {
		super();
	}

	public SaleOrderModel(SaleOrderId saleOrderId, String username, Date orderDate) {
		super();
		this.setSaleOrderId(saleOrderId);
		this.setUsername(username);
		this.setOrderDate(orderDate);
	}

	@Override
	public SaleOrderId saleOrderId() {
		return getSaleOrderId();
	}

	@Override
	public String username() {
		return getUsername();
	}

	@Override
	public Date orderDate() {
		return getOrderDate();
	}

	@Override
	public void changeUsername(String username) {
		setUsername(username);
	}

	@Override
	public void changeOrderDate(Date orderDate) {
		setOrderDate(orderDate);
	}

	@Override
	public void addSaleOrderItem(SaleOrderItem saleOrderItem) {
		getSaleOrderItems().add(saleOrderItem);
	}

}
