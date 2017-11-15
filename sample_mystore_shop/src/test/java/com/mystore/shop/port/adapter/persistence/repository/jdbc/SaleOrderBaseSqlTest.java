package com.mystore.shop.port.adapter.persistence.repository.jdbc;

import static org.junit.Assert.assertFalse;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mystore.shop.domain.model.order.SaleOrder;
import com.mystore.shop.domain.model.order.SaleOrderBase;
import com.mystore.shop.domain.model.order.SaleOrderFactory;
import com.mystore.shop.domain.model.order.SaleOrderId;
import com.mystore.shop.port.adapter.persistence.jdbc.SaleOrderBaseSql;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SaleOrderBaseSqlTest {

	private static final String CHANGED_USERNAME = "Lisi";

	@Autowired
	private SaleOrderFactory _saleOrderFactory;

	@Autowired
	private SaleOrderBaseSql _saleOrderBaseSql;

	@Test
	public void test() throws Exception {
		SaleOrderBase newObj = newSaleOrder();
		_saleOrderBaseSql.insert(newObj);
		SaleOrderBase retrievedObj = _saleOrderBaseSql.findOneById(newObj.getSaleOrderId());
		assertFalse(retrievedObj == null);

		retrievedObj.setUsername(CHANGED_USERNAME);
		_saleOrderBaseSql.update(retrievedObj);
		SaleOrderBase updatedObj = _saleOrderBaseSql.findOneById(retrievedObj.getSaleOrderId());
		assertFalse(updatedObj == null);
		assertFalse(!updatedObj.getUsername().equals(CHANGED_USERNAME));

		
		List<SaleOrderBase> list = _saleOrderBaseSql.findAll();
		assertFalse(list.size() <= 0);
		SaleOrderBase firstObj = list.get(0);
		assertFalse(firstObj == null);
		assertFalse(!updatedObj.getUsername().equals(CHANGED_USERNAME));

		_saleOrderBaseSql.deleteById(retrievedObj.getSaleOrderId());
		SaleOrderBase deletedObj = _saleOrderBaseSql.findOneById(retrievedObj.getSaleOrderId());
		assertFalse(deletedObj != null);
	}

	private SaleOrderBase newSaleOrder() {
		Random random = new Random();
		Long id = random.nextLong();
		SaleOrder saleOrder = _saleOrderFactory.saleOrder(new SaleOrderId(id),"zhangsan",new Date());
		return (SaleOrderBase) saleOrder;
	}
}
