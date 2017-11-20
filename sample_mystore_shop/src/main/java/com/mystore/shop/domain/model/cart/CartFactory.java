package com.mystore.shop.domain.model.cart;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.mystore.shop.domain.model.customer.CustomerId;

@Component
public class CartFactory {

	public Cart cart(CustomerId customerId, CartId cartId, Collection<CartItem> cartItems, BigDecimal total) {
		Cart cart = new Cart(customerId, cartId, cartItems, total);
		return cart;
	}
	
//	public Cart cart(CartBase cartBase) {
//		CartModel cartModel = new CartModel();
//		if (cartBase == null)
//			return null;
//		BeanUtils.copyProperties(cartBase, cartModel);
//		return cartModel;
//	}

//	public List<Cart> cartList(List<CartBase> cartBaseList) {
//		List<Cart> cartList = new ArrayList<Cart>();
//		for (CartBase base : cartBaseList) {
//			cartList.add(cart(base));
//		}
//		return cartList;
//	}
}
