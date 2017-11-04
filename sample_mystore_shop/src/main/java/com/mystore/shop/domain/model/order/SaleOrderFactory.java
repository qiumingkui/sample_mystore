package com.mystore.shop.domain.model.order;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class SaleOrderFactory {

	public SaleOrder saleOrder(SaleOrderId saleOrderId, String username, Date orderDate) {
		return new SaleOrderModel(saleOrderId, username, orderDate);
	}

}
