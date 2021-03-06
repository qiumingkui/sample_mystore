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
import com.mystore.shop.domain.model.productitem.ProductItemBase;
import com.mystore.shop.domain.model.productitem.ProductItemFactory;
import com.mystore.shop.domain.model.productitem.ProductItemId;
import com.mystore.shop.port.adapter.persistence.jdbc.ProductItemBaseSql;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductItemBaseSqlTest {

	private static final int CHANGED_QUANTITY = 9999;

	@Autowired
	private ProductItemFactory _productItemFactory;

	@Autowired
	private ProductItemBaseSql _productItemBaseSql;

	@Test
	public void test() throws Exception {
		ProductItemBase newObj = newProductItem();
		_productItemBaseSql.insert(newObj);
		ProductItemBase retrievedObj = _productItemBaseSql.findOneById(newObj.getProductItemId());
		assertFalse(retrievedObj == null);

		retrievedObj.setQuantity(CHANGED_QUANTITY);
		_productItemBaseSql.update(retrievedObj);
		ProductItemBase updatedObj = _productItemBaseSql.findOneById(retrievedObj.getProductItemId());
		assertFalse(updatedObj == null);
		assertFalse(!(updatedObj.getQuantity()==CHANGED_QUANTITY));

		List<ProductItemBase> list = _productItemBaseSql.findAll();
		assertFalse(list.size() <= 0);
		ProductItemBase firstObj = list.get(0);
		assertFalse(firstObj == null);
		assertFalse(!(updatedObj.getQuantity()==CHANGED_QUANTITY));

		List<ProductItemBase> equelsProductIdList=_productItemBaseSql.findAllByProductId(retrievedObj.getProductId());
		assertFalse(equelsProductIdList.size() <= 0);
		
		_productItemBaseSql.deleteById(retrievedObj.getProductItemId());
		ProductItemBase deletedObj = _productItemBaseSql.findOneById(retrievedObj.getProductItemId());
		assertFalse(deletedObj != null);
	}

	private ProductItemBase newProductItem() {
		Random random = new Random();
		Long id = random.nextLong();
		Long productId = random.nextLong();
		ProductItem category = _productItemFactory.productItem(new ProductItemId(id), new ProductId(productId),
				new BigDecimal(15), new BigDecimal(10), 100);
		return (ProductItemBase) category;
	}
}
