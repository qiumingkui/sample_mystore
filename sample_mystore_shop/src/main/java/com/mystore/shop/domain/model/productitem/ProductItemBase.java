package com.mystore.shop.domain.model.productitem;

import java.io.Serializable;
import java.math.BigDecimal;

import com.mystore.shop.domain.model.product.ProductId;

public class ProductItemBase implements Serializable {

	private static final long serialVersionUID = 1L;
	private ProductItemId productItemId;
	private ProductId productId;
	private BigDecimal listPrice;
	private BigDecimal unitCost;
	private int quantity;
	// private int supplierId;
	// private String status;

	public ProductItemBase(ProductItemId productItemId, ProductId productId, BigDecimal listPrice, BigDecimal unitCost,
			int quantity) {
		super();
		this.setProductItemId(productItemId);
		this.setProductId(productId);
		this.setListPrice(listPrice);
		this.setUnitCost(unitCost);
		this.setQuantity(quantity);
	}

	public ProductItemBase() {
		super();
	}

	public ProductItemId getProductItemId() {
		return productItemId;
	}

	public void setProductItemId(ProductItemId productItemId) {
		this.productItemId = productItemId;
	}

	public ProductId getProductId() {
		return productId;
	}

	public void setProductId(ProductId productId) {
		this.productId = productId;
	}

	public BigDecimal getListPrice() {
		return listPrice;
	}

	public void setListPrice(BigDecimal listPrice) {
		this.listPrice = listPrice;
	}

	public BigDecimal getUnitCost() {
		return unitCost;
	}

	public void setUnitCost(BigDecimal unitCost) {
		this.unitCost = unitCost;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
