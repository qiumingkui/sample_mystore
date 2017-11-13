package com.mystore.shop.domain.model.cart;

import java.util.List;

import com.mystore.shop.domain.model.customer.CustomerId;

public interface CartRepository {

	public void create(Cart cart);

	public void update(Cart cart);

	public void delete(CartId cartId);

	public Cart get(CartId cartId) ;

	public Cart getByCustomerId(CustomerId customerId);

	public List<Cart> getList() ;

}
