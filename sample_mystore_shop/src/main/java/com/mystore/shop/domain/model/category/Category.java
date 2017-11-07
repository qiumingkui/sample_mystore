package com.mystore.shop.domain.model.category;

public interface Category {

	public CategoryId categoryId();

	public String name() ;

	public String description() ;

	public void changeName(String name) ;

	public void changeDescription(String description) ;

}
