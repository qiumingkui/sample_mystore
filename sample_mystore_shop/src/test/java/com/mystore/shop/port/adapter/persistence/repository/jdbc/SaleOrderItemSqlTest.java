package com.mystore.shop.port.adapter.persistence.repository.jdbc;

import static org.junit.Assert.assertFalse;

import java.math.BigDecimal;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mystore.shop.domain.model.order.SaleOrderFactory;
import com.mystore.shop.domain.model.order.SaleOrderId;
import com.mystore.shop.domain.model.order.SaleOrderItemBase;
import com.mystore.shop.domain.model.order.SaleOrderItemId;
import com.mystore.shop.domain.model.productitem.ProductItemId;
import com.mystore.shop.port.adapter.persistence.jdbc.SaleOrderItemBaseSql;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SaleOrderItemSqlTest {

	private static final int CHANGED_QUANTITY = 9999;

	@Autowired
	private SaleOrderItemBaseSql saleOrderItemBaseSql;

	@Autowired
	private SaleOrderFactory saleOrderFactory;

	@Test
	public void test() throws Exception {
		SaleOrderItemBase newObj = newSaleOrderItemBase();
		saleOrderItemBaseSql.insert(newObj);
		SaleOrderItemBase retrievedObj = saleOrderItemBaseSql.findOneById(newObj.getSaleOrderItemId());
		assertFalse(retrievedObj == null);

		retrievedObj.setQuantity(CHANGED_QUANTITY);
		saleOrderItemBaseSql.update(retrievedObj);
		SaleOrderItemBase updatedObj = saleOrderItemBaseSql.findOneById(retrievedObj.getSaleOrderItemId());
		assertFalse(updatedObj == null);
		assertFalse(!(updatedObj.getQuantity()==CHANGED_QUANTITY));

		saleOrderItemBaseSql.deleteById(retrievedObj.getSaleOrderItemId());
		SaleOrderItemBase deletedObj = saleOrderItemBaseSql.findOneById(retrievedObj.getSaleOrderItemId());
		assertFalse(deletedObj != null);

	}

	private SaleOrderItemBase newSaleOrderItemBase() {
		Random random = new Random();
		Long saleOrderItemId = random.nextLong();
		Long saleOrderId = random.nextLong();
		Long productItemId = random.nextLong();
		SaleOrderItemBase cartItem = (SaleOrderItemBase) saleOrderFactory.saleOrderItem(new SaleOrderItemId(saleOrderItemId),
				new SaleOrderId(saleOrderId), new ProductItemId(productItemId), 5, new BigDecimal(2),
				new BigDecimal(10));
		return cartItem;
	}
}
