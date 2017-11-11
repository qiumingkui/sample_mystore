package com.mystore.shop.domain.model.productitem;

import javax.persistence.Embeddable;

@Embeddable
public class ProductItemId {

	private long id;

	protected ProductItemId() {
		super();
	}

	public ProductItemId(long id) {
		super();
		setId(id);
	}

	public long getId() {
		return id;
	}

	protected void setId(long id) {
		this.id = id;
	}

	public boolean equals(ProductItemId productItemId) {
		if (this.id == productItemId.getId()) {
			return true;
		}
		return false;
	}
}
