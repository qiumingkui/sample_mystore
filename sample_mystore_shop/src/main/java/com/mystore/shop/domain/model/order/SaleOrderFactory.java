package com.mystore.shop.domain.model.order;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.mystore.shop.domain.model.productitem.ProductItemId;

@Component
public class SaleOrderFactory {

	// public SaleOrder saleOrder(String username, Date orderDate) {
	//
	// SaleOrderId saleOrderId = saleOrderRepository.nextId();
	// return saleOrder(saleOrderId, username, orderDate);
	// }

	public SaleOrder saleOrder(SaleOrderId saleOrderId, String username, Date orderDate) {
		return new SaleOrder(saleOrderId, username, orderDate);
	}

	public SaleOrderItem saleOrderItem(SaleOrderItemId saleOrderItemId, SaleOrderId saleOrderId,
			ProductItemId productItemId, int quantity, BigDecimal unitPrice, BigDecimal total) {
		return new SaleOrderItem(saleOrderItemId, saleOrderId, productItemId, quantity, unitPrice, total);
	}
}
