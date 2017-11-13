package com.mystore.shop.domain.model.order;

import java.math.BigDecimal;

import com.mystore.shop.domain.model.productitem.ProductItemId;

public class SaleOrderItemModel extends SaleOrderItemBase implements SaleOrderItem {
	private static final long serialVersionUID = -1L;

	public SaleOrderItemModel() {
		super();
	}

	public SaleOrderItemModel(SaleOrderItemId saleOrderItemId, int quantity, BigDecimal unitPrice, BigDecimal total,
			ProductItemId productItemId) {
		super(saleOrderItemId, quantity, unitPrice, total, productItemId);
	}

	@Override
	public SaleOrderItemId saleOrderItemId() {
		return getSaleOrderItemId();
	}

	@Override
	public int quantity() {
		return getQuantity();
	}

	@Override
	public BigDecimal unitPrice() {
		return getUnitPrice();
	}

	@Override
	public BigDecimal total() {
		return getTotal();
	}

	@Override
	public ProductItemId productItemId() {
		return getProductItemId();
	}

}
