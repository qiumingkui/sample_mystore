package com.mystore.shop.domain.model.productitem;

import java.math.BigDecimal;

import com.mystore.shop.domain.model.product.ProductId;

public class ProductItemModel extends ProductItemBase implements ProductItem {

	private static final long serialVersionUID = 1L;

	public ProductItemModel() {
		super();
	}

	public ProductItemModel(ProductItemId productItemId, ProductId productId, BigDecimal listPrice, BigDecimal unitCost,
			int quantity) {
		super(productItemId, productId, listPrice, unitCost, quantity);
	}

	@Override
	public ProductItemId productItemId() {
		return this.getProductItemId();
	}

	@Override
	public ProductId productId() {
		return this.getProductId();
	}

	@Override
	public BigDecimal listPrice() {
		return this.getListPrice();
	}

	@Override
	public BigDecimal unitCost() {
		return this.getUnitCost();
	}

	@Override
	public int quantity() {
		return this.getQuantity();
	}

	@Override
	public void changeListPrice(BigDecimal listPrice) {
		this.setListPrice(listPrice);
	}

	@Override
	public void changeUnitCost(BigDecimal unitCost) {
		this.setUnitCost(unitCost);
	}

	@Override
	public void changeQuantity(int quantity) {
		this.setQuantity(quantity);
	}

}
