package com.mystore.shop.domain.model.category;

public class CategoryId  {

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
