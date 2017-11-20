package com.mystore.shop.domain.model.product;

import com.mystore.common.utils.AssertionConcern;

public class ProductValidator extends AssertionConcern {
	
	public void assertName(String name) {
		this.assertArgumentNotNull(name, "Name must not be null!");
		this.assertArgumentNotEmpty(name, "Name must not be empty!");
	}

	public void assertDescription(String description) {
		this.assertArgumentNotNull(description, "Description must not be null!");
		this.assertArgumentNotEmpty(description, "Description must not be empty!");
	}

}
