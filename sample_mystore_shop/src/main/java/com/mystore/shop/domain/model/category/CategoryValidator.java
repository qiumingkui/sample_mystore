package com.mystore.shop.domain.model.category;

import com.mystore.common.utils.AssertionConcern;

public final class CategoryValidator extends AssertionConcern{
	
	public void assertName(String name){
		this.assertArgumentNotNull(name, "Name must not be null!");
		this.assertArgumentNotEmpty(name, "Name must not be empty!");
	}

	public void assertDescription(String description){
		this.assertArgumentNotNull(description, "Description must not be null!");
		this.assertArgumentNotEmpty(description, "Description must not be empty!");
	}

}
