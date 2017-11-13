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
		return new SaleOrderModel(saleOrderId, username, orderDate);
	}

	public SaleOrderItem saleOrderItem(SaleOrderItemId saleOrderItemId, int quantity, BigDecimal unitPrice,
			BigDecimal total, ProductItemId productItemId) {
		return new SaleOrderItemModel(saleOrderItemId, quantity, unitPrice, total, productItemId);
	}
}
