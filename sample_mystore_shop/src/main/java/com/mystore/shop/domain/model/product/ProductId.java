package com.mystore.shop.domain.model.product;

public class ProductId {

	private long id;

	public ProductId(long id) {
		super();
		setId(id);
	}

	public long getId() {
		return id;
	}

	protected void setId(long id) {
		this.id = id;
	}

}
