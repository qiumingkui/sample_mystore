package com.mystore.shop.domain.model.order;

import java.util.Date;

public interface SaleOrder {
	public int orderId();

	public String username();

	public Date orderDate();

	public void changeUsername(String username);

	public void changeOrderDate(Date orderDate);
}
