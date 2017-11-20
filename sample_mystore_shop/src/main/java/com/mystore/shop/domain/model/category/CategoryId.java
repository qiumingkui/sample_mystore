package com.mystore.shop.domain.model.category;

import java.io.Serializable;

public class CategoryId  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public static final String ID="id";
	
	private long id;

	public CategoryId() {
		super();
	}

	public CategoryId(long id) {
		super();
		this.id = id;
	}

	public long id() {
		return this.id;
	}

	public long getId() {
		return this.id;
	}

	protected void setId(long id) {
		this.id = id;
	}

}
