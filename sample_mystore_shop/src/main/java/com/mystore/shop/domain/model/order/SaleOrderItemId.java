package com.mystore.shop.domain.model.order;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class SaleOrderItemId implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id = -1;

	public SaleOrderItemId() {
		super();
	}

	public SaleOrderItemId(Long id) {
		super();
		this.id = id;
	}

	public long getId() {
		return this.id;
	}

	protected void setId(Long id) {
		this.id = id;
	}

}
