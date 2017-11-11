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

import com.mystore.shop.domain.model.product.ProductId;
import com.mystore.shop.domain.model.productitem.ProductItem;
import com.mystore.shop.domain.model.productitem.ProductItemFactory;
import com.mystore.shop.domain.model.productitem.ProductItemId;
import com.mystore.shop.port.adapter.persistence.jdbc.ProductItemRepositorySql;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductItemRepositorySqlTest {

	private static final int CHANGED_QUANTITY = 9999;

	@Autowired
	private ProductItemFactory _productItemFactory;

	@Autowired
	private ProductItemRepositorySql _productItemRepositorySql;

	@Test
	public void test() throws Exception {
		ProductItem newObj = newProductItem();
		_productItemRepositorySql.create(newObj);
		ProductItem retrievedObj = _productItemRepositorySql.get(newObj.productItemId());
		assertFalse(retrievedObj == null);

		retrievedObj.changeQuantity(CHANGED_QUANTITY);
		_productItemRepositorySql.update(retrievedObj);
		ProductItem updatedObj = _productItemRepositorySql.get(retrievedObj.productItemId());
		assertFalse(updatedObj == null);
		assertFalse(!(updatedObj.quantity() == CHANGED_QUANTITY));

		List<ProductItem> list = _productItemRepositorySql.getProductItemList();
		assertFalse(list.size() <= 0);
		ProductItem firstObj = list.get(0);
		assertFalse(firstObj == null);
		assertFalse(!(updatedObj.quantity() == CHANGED_QUANTITY));

		// _productItemRepositorySql.delete(retrievedObj.productItemId());
		// ProductItem deletedObj =
		// _productItemRepositorySql.get(retrievedObj.productItemId());
		// assertFalse(deletedObj != null);
	}

	private ProductItem newProductItem() {
		Random random = new Random();
		Long id = random.nextLong();
		Long productId = random.nextLong();
		ProductItem category = _productItemFactory.productItem(new ProductItemId(id), new ProductId(productId),
				new BigDecimal(15), new BigDecimal(10), 100);
		return category;
	}
}
