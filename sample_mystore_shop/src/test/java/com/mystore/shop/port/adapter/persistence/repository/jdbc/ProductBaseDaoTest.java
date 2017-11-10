package com.mystore.shop.port.adapter.persistence.repository.jdbc;

import static org.junit.Assert.assertFalse;

import java.util.List;
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
import com.mystore.shop.port.adapter.persistence.jdbc.ProductBaseSql;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductBaseDaoTest {

	private static final String CHANGED_DES = "This is changed Book!";

	@Autowired
	private ProductFactory _productFactory;

	@Autowired
	private ProductBaseSql _productBaseDao;

	@Test
	public void test() throws Exception {
		ProductBase newObj = newProduct();
		_productBaseDao.insert(newObj);
		ProductBase retrievedObj = _productBaseDao.findById(newObj.getProductId());
		assertFalse(retrievedObj == null);

		retrievedObj.setDescription(CHANGED_DES);
		_productBaseDao.update(retrievedObj);
		ProductBase updatedObj = _productBaseDao.findById(retrievedObj.getProductId());
		assertFalse(updatedObj == null);
		assertFalse(!updatedObj.getDescription().equals(CHANGED_DES));

		List<ProductBase> list = _productBaseDao.findAll();
		assertFalse(list.size() <= 0);
		ProductBase firstObj = list.get(0);
		assertFalse(firstObj == null);
		assertFalse(!updatedObj.getDescription().equals(CHANGED_DES));

		_productBaseDao.deleteById(retrievedObj.getProductId());
		ProductBase deletedObj = _productBaseDao.findById(retrievedObj.getProductId());
		assertFalse(deletedObj != null);
	}

	private ProductBase newProduct() {
		Random random = new Random();
		Long id = random.nextLong();
		Product product = _productFactory.product(new ProductId(id), null, "book", "This is book!");
		return (ProductBase) product;
	}
}
