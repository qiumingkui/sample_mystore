package com.mystore.shop.domain.model.order;

import java.io.Serializable;
import java.util.Date;

public interface SaleOrder extends Serializable {

	public SaleOrderId saleOrderId();

	public String username();

	public Date orderDate();

	public void changeUsername(String username);

	public void changeOrderDate(Date orderDate);

}
