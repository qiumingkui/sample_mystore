package com.mystore.shop.domain.model.order;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaleOrderFactory {
	@Autowired
	private SaleOrderRepository saleOrderRepository;

	public SaleOrder saleOrder(String username, Date orderDate) {

		SaleOrderId saleOrderId = saleOrderRepository.nextId();
		return saleOrder(saleOrderId, username, orderDate);
	}

	protected SaleOrder saleOrder(SaleOrderId saleOrderId, String username, Date orderDate) {
		return new SaleOrderModel(saleOrderId, username, orderDate);
	}

}
