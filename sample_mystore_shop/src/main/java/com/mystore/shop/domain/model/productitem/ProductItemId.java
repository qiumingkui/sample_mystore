package com.mystore.shop.domain.model.productitem;

public class ProductItemId {

	private long _id;

	public ProductItemId(long id) {
		super();
		setId(id);
	}

	public long id() {
		return _id;
	}

	protected void setId(long id) {
		this._id = id;
	}

}
