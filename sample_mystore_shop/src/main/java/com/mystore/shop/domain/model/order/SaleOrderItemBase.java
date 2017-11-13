package com.mystore.shop.domain.model.order;

import java.math.BigDecimal;

import com.mystore.shop.domain.model.productitem.ProductItemId;

public class SaleOrderItemBase {
	private SaleOrderItemId saleOrderItemId;

	private int quantity;

	private BigDecimal unitPrice;

	private BigDecimal total;

	private ProductItemId productItemId;

	public SaleOrderItemBase() {
		super();
	}

	public SaleOrderItemBase(SaleOrderItemId saleOrderItemId, int quantity, BigDecimal unitPrice, BigDecimal total,
			ProductItemId productItemId) {
		super();
		setSaleOrderItemId(saleOrderItemId);
		setQuantity(quantity);
		setUnitPrice(unitPrice);
		setTotal(total);
		setProductItemId(productItemId);
	}

	public SaleOrderItemId getSaleOrderItemId() {
		return saleOrderItemId;
	}

	public void setSaleOrderItemId(SaleOrderItemId saleOrderItemId) {
		this.saleOrderItemId = saleOrderItemId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public ProductItemId getProductItemId() {
		return productItemId;
	}

	public void setProductItemId(ProductItemId productItemId) {
		this.productItemId = productItemId;
	}
}
