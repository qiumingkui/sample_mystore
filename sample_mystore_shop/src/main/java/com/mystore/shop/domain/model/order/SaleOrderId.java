package com.mystore.shop.domain.model.order;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SaleOrderId implements Serializable {

	private static final long serialVersionUID = 1L;

	private long _id = -1;

	public SaleOrderId() {
		super();
	}

	public SaleOrderId(Long id) {
		super();
		this._id = id;
	}

	public long id() {
		return this._id;
	}

	protected void setId(Long id) {
		this._id = id;
	}

}
