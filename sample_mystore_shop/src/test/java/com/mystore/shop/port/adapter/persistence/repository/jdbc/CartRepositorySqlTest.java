package com.mystore.shop.port.adapter.persistence.repository.jdbc;

import static org.junit.Assert.assertFalse;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mystore.shop.domain.model.cart.Cart;
import com.mystore.shop.domain.model.cart.CartFactory;
import com.mystore.shop.domain.model.cart.CartId;
import com.mystore.shop.domain.model.cart.CartItem;
import com.mystore.shop.domain.model.customer.CustomerId;
import com.mystore.shop.domain.model.productitem.ProductItemId;
import com.mystore.shop.port.adapter.persistence.jdbc.CartRepositorySql;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartRepositorySqlTest {

	@Autowired
	private CartFactory _cartFactory;

	@Autowired
	private CartRepositorySql _cartRepositorySql;

	@Test
	public void test() throws Exception {
		Cart newObj = newCart();
		CartItem cartItemOne = newCartItem(newObj.cartId());
		CartItem cartItemTwo = newCartItem(newObj.cartId());

		_cartRepositorySql.create(newObj);
		Cart retrievedObj = _cartRepositorySql.get(newObj.cartId());
		assertFalse(retrievedObj == null);

		retrievedObj.addCartItem(cartItemOne);
		retrievedObj.addCartItem(cartItemTwo);
		_cartRepositorySql.update(retrievedObj);
		Cart updatedObj = _cartRepositorySql.get(retrievedObj.cartId());
		assertFalse(updatedObj == null);
		assertFalse(updatedObj.cartItem(cartItemOne.getProductItemId()) == null);
		assertFalse(updatedObj.cartItem(cartItemTwo.getProductItemId()) == null);

		List<Cart> list = _cartRepositorySql.getList();
		assertFalse(list.size() <= 0);
		Cart firstObj = list.get(0);
		assertFalse(firstObj == null);

		_cartRepositorySql.delete(retrievedObj.cartId());
		Cart deletedObj = _cartRepositorySql.get(retrievedObj.cartId());
		assertFalse(deletedObj != null);
	}

	private Cart newCart() {
		Random random = new Random();
		Long id = random.nextLong();
		Long customerId = random.nextLong();
		Cart cart = _cartFactory.cart(new CustomerId(customerId), new CartId(id), new ArrayList<CartItem>(),
				new BigDecimal(15));
		return cart;
	}

	private CartItem newCartItem(CartId cartId) {
		Random random = new Random();
		Long piId = random.nextLong();
		CartItem cartItem = new CartItem(cartId, new ProductItemId(piId), 3, new BigDecimal(2), new BigDecimal(1));
		return cartItem;
	}
}
