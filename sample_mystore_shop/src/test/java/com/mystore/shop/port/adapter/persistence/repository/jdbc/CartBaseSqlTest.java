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
import com.mystore.shop.domain.model.cart.CartBase;
import com.mystore.shop.domain.model.cart.CartFactory;
import com.mystore.shop.domain.model.cart.CartId;
import com.mystore.shop.domain.model.customer.CustomerId;
import com.mystore.shop.domain.model.product.ProductId;
import com.mystore.shop.port.adapter.persistence.jdbc.CartBaseSql;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartBaseSqlTest {

	private static final BigDecimal CHANGED_TOTAL = new BigDecimal(99);

	@Autowired
	private CartFactory _cartFactory;

	@Autowired
	private CartBaseSql _cartBaseSql;

	@Test
	public void test() throws Exception {
		CartBase newObj = newCart();
		_cartBaseSql.insert(newObj);
		CartBase retrievedObj = _cartBaseSql.findOneById(newObj.getCartId());
		assertFalse(retrievedObj == null);

		retrievedObj.setTotal(CHANGED_TOTAL);
		_cartBaseSql.update(retrievedObj);
		CartBase updatedObj = _cartBaseSql.findOneById(retrievedObj.getCartId());
		assertFalse(updatedObj == null);
		assertFalse(!(updatedObj.getTotal().equals(CHANGED_TOTAL)));

		List<CartBase> list = _cartBaseSql.findAll();
		assertFalse(list.size() <= 0);
		CartBase firstObj = list.get(0);
		assertFalse(firstObj == null);
		assertFalse(!(updatedObj.getTotal().equals(CHANGED_TOTAL)));

		List<CartBase> customerIdList=_cartBaseSql.findAllByCustomerId(retrievedObj.getCustomerId());
		assertFalse(customerIdList.size() <= 0);
		
		_cartBaseSql.deleteById(retrievedObj.getCartId());
		CartBase deletedObj = _cartBaseSql.findOneById(retrievedObj.getCartId());
		assertFalse(deletedObj != null);
	}

	private CartBase newCart() {
		Random random = new Random();
		Long id = random.nextLong();
		Long customerId = random.nextLong();
		Cart cart = _cartFactory.cart(new CustomerId(customerId), new CartId(id),null,
				new BigDecimal(15));
		return (CartBase) cart;
	}
}
