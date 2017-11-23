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
import com.mystore.shop.domain.model.order.SaleOrderItem;
import com.mystore.shop.domain.model.order.SaleOrderItemId;
import com.mystore.shop.domain.model.productitem.ProductItemId;
import com.mystore.shop.port.adapter.persistence.jdbc.SaleOrderItemSql;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SaleOrderItemSqlTest {

	private static final int CHANGED_QUANTITY = 9999;

	@Autowired
	private SaleOrderItemSql saleOrderItemBaseSql;

	@Autowired
	private SaleOrderFactory saleOrderFactory;

	@Test
	public void test() throws Exception {
		SaleOrderItem newObj = newSaleOrderItem();
		saleOrderItemBaseSql.insert(newObj);
		SaleOrderItem retrievedObj = saleOrderItemBaseSql.findOneById(newObj.getSaleOrderItemId());
		assertFalse(retrievedObj == null);

		retrievedObj.setQuantity(CHANGED_QUANTITY);
		saleOrderItemBaseSql.update(retrievedObj);
		SaleOrderItem updatedObj = saleOrderItemBaseSql.findOneById(retrievedObj.getSaleOrderItemId());
		assertFalse(updatedObj == null);
		assertFalse(!(updatedObj.getQuantity() == CHANGED_QUANTITY));

		// List<SaleOrderItem> list = saleOrderItemBaseSql.findAll();
		// assertFalse(list.size() <= 0);
		// SaleOrderItem firstObj = list.get(0);
		// assertFalse(firstObj == null);
		// assertFalse(!(updatedObj.getQuantity()==CHANGED_QUANTITY));
		//
		// List<SaleOrderItem>
		// equelsProductIdList=saleOrderItemBaseSql.findAllByProductId(retrievedObj.getProductId());
		// assertFalse(equelsProductIdList.size() <= 0);

		saleOrderItemBaseSql.deleteById(retrievedObj.getSaleOrderItemId());
		SaleOrderItem deletedObj = saleOrderItemBaseSql.findOneById(retrievedObj.getSaleOrderItemId());
		assertFalse(deletedObj != null);

	}

	private SaleOrderItem newSaleOrderItem() {
		Random random = new Random();
		Long saleOrderItemId = random.nextLong();
		Long saleOrderId = random.nextLong();
		Long productItemId = random.nextLong();
		SaleOrderItem cartItem = saleOrderFactory.saleOrderItem(new SaleOrderItemId(saleOrderItemId),
				new SaleOrderId(saleOrderId), new ProductItemId(productItemId), 5, new BigDecimal(2),
				new BigDecimal(10));
		return cartItem;
	}
}
