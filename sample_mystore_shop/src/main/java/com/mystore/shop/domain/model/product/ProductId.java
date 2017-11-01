package com.mystore.shop.domain.model.product;

import com.mystore.common.domain.annotation.DomainEntityId;

@DomainEntityId
public class ProductId {

	private long _id;

	public ProductId(long id) {
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
