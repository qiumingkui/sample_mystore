package com.mystore.shop.domain.model.category;

import java.io.Serializable;

public class CategoryId  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private long _id;

	public CategoryId() {
		super();
	}

	public CategoryId(long id) {
		super();
		this._id = id;
	}

	public long id() {
		return this._id;
	}

	public long getId() {
		return this._id;
	}

	protected void setId(long id) {
		this._id = id;
	}

}
