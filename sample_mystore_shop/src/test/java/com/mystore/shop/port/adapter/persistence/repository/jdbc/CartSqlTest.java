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

import com.mystore.shop.domain.model.cart.Cart;
import com.mystore.shop.domain.model.cart.CartFactory;
import com.mystore.shop.domain.model.cart.CartId;
import com.mystore.shop.domain.model.customer.CustomerId;
import com.mystore.shop.port.adapter.persistence.jdbc.CartSql;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartSqlTest {

	private static final BigDecimal CHANGED_TOTAL = new BigDecimal(99);

	@Autowired
	private CartFactory _cartFactory;

	@Autowired
	private CartSql _cartSql;

	@Test
	public void test() throws Exception {
		Cart newObj = newCart();
		_cartSql.insert(newObj);
		Cart retrievedObj = _cartSql.findOneById(newObj.getCartId());
		assertFalse(retrievedObj == null);

		retrievedObj.setTotal(CHANGED_TOTAL);
		_cartSql.update(retrievedObj);
		Cart updatedObj = _cartSql.findOneById(retrievedObj.getCartId());
		assertFalse(updatedObj == null);
		assertFalse(!(updatedObj.getTotal().equals(CHANGED_TOTAL)));

		List<Cart> list = _cartSql.findAll();
		assertFalse(list.size() <= 0);
		Cart firstObj = list.get(0);
		assertFalse(firstObj == null);
		assertFalse(!(updatedObj.getTotal().equals(CHANGED_TOTAL)));

		List<Cart> customerIdList=_cartSql.findAllByCustomerId(retrievedObj.getCustomerId());
		assertFalse(customerIdList.size() <= 0);
		
		_cartSql.deleteById(retrievedObj.getCartId());
		Cart deletedObj = _cartSql.findOneById(retrievedObj.getCartId());
		assertFalse(deletedObj != null);
	}

	private Cart newCart() {
		Random random = new Random();
		Long id = random.nextLong();
		Long customerId = random.nextLong();
		Cart cart = _cartFactory.cart(new CustomerId(customerId), new CartId(id),null,
				new BigDecimal(15));
		return cart;
	}
}
