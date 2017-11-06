package com.mystore.shop.domain.model.customer;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class CustomerId implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id = -1;

	public CustomerId() {
		super();
	}

	public CustomerId(Long id) {
		super();
		this.id = id;
	}

	public long id() {
		return this.id;
	}

	protected void setId(Long id) {
		this.id = id;
	}

}
