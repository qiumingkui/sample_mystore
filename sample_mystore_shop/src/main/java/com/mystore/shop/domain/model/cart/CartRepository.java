package com.mystore.shop.domain.model.cart;

import java.util.List;

public interface CartRepository {

	public Cart get(CartId cartId) throws Exception;

	public void create(Cart cart);

	public void update(Cart cart);

	public void delete(CartId cartId);

	public List<Cart> getList() throws Exception;

}
