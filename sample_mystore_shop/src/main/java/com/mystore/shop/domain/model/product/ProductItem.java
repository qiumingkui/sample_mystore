package com.mystore.shop.domain.model.product;

import java.math.BigDecimal;

public class ProductItem {

	private ProductItemId _productItemId;
	private ProductId _productId;
	private BigDecimal _listPrice;
	private BigDecimal _unitCost;
	private int _quantity;
	private int _supplierId;
	private String _status;

	public ProductItemId productItemId() {
		return _productItemId;
	}

	public ProductId productId() {
		return _productId;
	}

	public BigDecimal listPrice() {
		return _listPrice;
	}

	public BigDecimal unitCost() {
		return _unitCost;
	}

	public int supplierId() {
		return _supplierId;
	}

	public String status() {
		return _status;
	}

	public int getQuantity() {
		return _quantity;
	}

	public void changeListPrice(BigDecimal listPrice){
		setListPrice(listPrice);
	}
	
	public void changeUnitCost(BigDecimal unitCost){
		setUnitCost(unitCost);
	}
	
	public void changeQuantity(int quantity){
		setQuantity(quantity);
	}
	
	protected void setProductItemId(ProductItemId productItemId) {
		this._productItemId = productItemId;
	}

	protected void setProductId(ProductId productId) {
		this._productId = productId;
	}

	protected void setListPrice(BigDecimal listPrice) {
		this._listPrice = listPrice;
	}

	protected void setUnitCost(BigDecimal unitCost) {
		this._unitCost = unitCost;
	}

	protected void setSupplierId(int supplierId) {
		this._supplierId = supplierId;
	}

	protected void setStatus(String status) {
		this._status = status;
	}

	protected void setQuantity(int quantity) {
		this._quantity = quantity;
	}

}
