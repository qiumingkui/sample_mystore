package com.mystore.shop.domain.model.order;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class SaleOrderId implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String ID = "id";

	private long id = -1;

	public SaleOrderId() {
		super();
	}

	public SaleOrderId(Long id) {
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
