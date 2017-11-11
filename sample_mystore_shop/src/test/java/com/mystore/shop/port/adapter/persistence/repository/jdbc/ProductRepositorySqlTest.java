package com.mystore.shop.port.adapter.persistence.repository.jdbc;

import static org.junit.Assert.assertFalse;

import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mystore.shop.domain.model.product.Product;
import com.mystore.shop.domain.model.product.ProductFactory;
import com.mystore.shop.domain.model.product.ProductId;
import com.mystore.shop.port.adapter.persistence.jdbc.ProductRepositorySql;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductRepositorySqlTest {

	private static final String CHANGED_DES = "This is changed english Book!";

	@Autowired
	private ProductFactory _productFactory;

	@Autowired
	private ProductRepositorySql _productRepositorySql;

	@Test
	public void test() throws Exception {
		Product newObj = newProduct();
		_productRepositorySql.create(newObj);
		Product retrievedObj = _productRepositorySql.get(newObj.productId());
		assertFalse(retrievedObj == null);

		retrievedObj.changeDescription(CHANGED_DES);
		_productRepositorySql.update(retrievedObj);
		Product updatedObj = _productRepositorySql.get(retrievedObj.productId());
		assertFalse(updatedObj == null);
		assertFalse(!updatedObj.description().equals(CHANGED_DES));

		_productRepositorySql.delete(retrievedObj.productId());
		Product deletedObj = _productRepositorySql.get(retrievedObj.productId());
		assertFalse(deletedObj != null);

	}

	private Product newProduct() {
		Random random = new Random();
		Long id = random.nextLong();
		Product product = _productFactory.product(new ProductId(id), null, "book", "This is book!");
		return product;
	}
}
