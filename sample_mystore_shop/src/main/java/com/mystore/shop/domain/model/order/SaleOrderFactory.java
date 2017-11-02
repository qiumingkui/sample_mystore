package com.mystore.shop.domain.model.order;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class SaleOrderFactory {

	public SaleOrder saleOrder(String username, Date orderDate) {
		return new SaleOrder(username, orderDate);
	}

}
