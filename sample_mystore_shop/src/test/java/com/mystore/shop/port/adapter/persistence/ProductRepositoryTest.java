package com.mystore.shop.port.adapter.persistence;

import static org.junit.Assert.assertFalse;

import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mystore.shop.domain.model.product.Product;
import com.mystore.shop.domain.model.product.ProductBase;
import com.mystore.shop.domain.model.product.ProductFactory;
import com.mystore.shop.domain.model.product.ProductId;
import com.mystore.shop.domain.model.product.ProductRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductRepositoryTest {

	private static final String CHANGED_DES = "This is changed english Book!";

	@Autowired
	private ProductFactory _productFactory;

	@Autowired
	private ProductRepository _productRepository;

	@Test
	public void covered() {
		Product newObj = newProduct();
		_productRepository.create(newObj);
		Product retrievedObj = _productRepository.get(newObj.productId());
		assertFalse(retrievedObj == null);

		retrievedObj.changeDescription(CHANGED_DES);
		_productRepository.update(retrievedObj);
		Product updatedObj = _productRepository.get(retrievedObj.productId());
		assertFalse(updatedObj == null);
		assertFalse(!updatedObj.description().equals(CHANGED_DES));

	}

	private Product newProduct() {
		Random random = new Random();
		Long id = random.nextLong();
		Product product = _productFactory.product(new ProductId(id), null, "book", "This is book!");
		return product;
	}
}
