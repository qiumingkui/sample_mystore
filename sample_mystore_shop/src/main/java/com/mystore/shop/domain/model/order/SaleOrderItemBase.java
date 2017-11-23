package com.mystore.shop.domain.model.order;

import java.math.BigDecimal;

import com.mystore.shop.domain.model.productitem.ProductItemId;

public class SaleOrderItemBase {

	public static final String SALEORDERITEMID = "saleOrderItemId";
	public static final String SALEORDERID = "saleOrderId";
	public static final String PRODUCTITEMID = "productItemId";
	public static final String QUANTITY = "quantity";
	public static final String UNITPRICE = "unitPrice";
	public static final String TOTAL = "total";

	private SaleOrderItemId saleOrderItemId;

	private SaleOrderId saleOrderId;

	private ProductItemId productItemId;

	private int quantity;

	private BigDecimal unitPrice;

	private BigDecimal total;

	public SaleOrderItemBase() {
		super();
	}

	public SaleOrderItemBase(SaleOrderItemId saleOrderItemId, SaleOrderId saleOrderId, ProductItemId productItemId,
			int quantity, BigDecimal unitPrice, BigDecimal total) {
		super();
		this.saleOrderItemId = saleOrderItemId;
		this.saleOrderId = saleOrderId;
		this.productItemId = productItemId;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.total = total;
	}

	public SaleOrderItemId getSaleOrderItemId() {
		return saleOrderItemId;
	}

	public void setSaleOrderItemId(SaleOrderItemId saleOrderItemId) {
		this.saleOrderItemId = saleOrderItemId;
	}

	public SaleOrderId getSaleOrderId() {
		return saleOrderId;
	}

	public void setSaleOrderId(SaleOrderId saleOrderId) {
		this.saleOrderId = saleOrderId;
	}

	public ProductItemId getProductItemId() {
		return productItemId;
	}

	public void setProductItemId(ProductItemId productItemId) {
		this.productItemId = productItemId;
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
}
