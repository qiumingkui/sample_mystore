package com.mystore.shop.domain.model.cart;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class CartId implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id = -1;

	public CartId() {
		super();
	}

	public CartId(Long id) {
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
