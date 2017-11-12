package com.mystore.shop.port.adapter.persistence.repository.jdbc;

import static org.junit.Assert.assertFalse;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mystore.shop.domain.model.cart.CartItem;
import com.mystore.shop.domain.model.cart.CartId;
import com.mystore.shop.domain.model.productitem.ProductItemId;
import com.mystore.shop.port.adapter.persistence.jdbc.CartItemSql;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartItemSqlTest {

	@Autowired
	private CartItemSql _cartItemSql;

	@Test
	public void test() throws Exception {
		CartItem newObj = newCart();
		_cartItemSql.insert(newObj);

		List<CartItem> list = _cartItemSql.findAllByFK(newObj.getCartId());
		assertFalse(list.size() <= 0);
		CartItem firstObj = list.get(0);
		assertFalse(firstObj == null);
		assertFalse(!(newObj.getTotal().equals(firstObj.getTotal())));

		_cartItemSql.deleteByFK(newObj.getCartId());
		List<CartItem> deletedList = _cartItemSql.findAllByFK(newObj.getCartId());
		assertFalse(deletedList.size() > 0);
	}

	private CartItem newCart() {
		Random random = new Random();
		Long cartId = random.nextLong();
		Long piId = random.nextLong();
		CartItem cartItem = new CartItem(new CartId(cartId), new ProductItemId(piId), 3, new BigDecimal(2),
				new BigDecimal(1));
		return cartItem;
	}
}
