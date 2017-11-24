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
import com.mystore.shop.domain.model.order.SaleOrderFactory;
import com.mystore.shop.domain.model.order.SaleOrderId;
import com.mystore.shop.port.adapter.persistence.jdbc.SaleOrderSql;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SaleOrderSqlTest {

	private static final String USERNAME = "zhangshan";
	private static final String CHANGED_USERNAME = "zhangshan123";

	@Autowired
	private SaleOrderSql _saleOrderSql;

	@Autowired
	private SaleOrderFactory saleOrderFactory;

	@Test
	public void test() throws Exception {
		SaleOrder newObj = newSaleOrder();
		_saleOrderSql.insert(newObj);
		SaleOrder retrievedObj = _saleOrderSql.findOneById(newObj.saleOrderId());
		assertFalse(retrievedObj == null);

		retrievedObj.changeUsername(CHANGED_USERNAME);
		_saleOrderSql.update(retrievedObj);
		SaleOrder updatedObj = _saleOrderSql.findOneById(retrievedObj.saleOrderId());
		assertFalse(updatedObj == null);
		assertFalse(!updatedObj.username().equals(CHANGED_USERNAME));

		// List<SaleOrder> likeNameList = _saleOrderSql.findAllByNameLike(BOOK);
		// assertFalse(likeNameList.size() <= 0);
		// likeNameList = _saleOrderSql.findAllByNameLike(PEN);
		// assertFalse(likeNameList.size() > 0);

		List<SaleOrder> list = _saleOrderSql.findAll();
		assertFalse(list.size() <= 0);
		SaleOrder firstObj = list.get(0);
		assertFalse(firstObj == null);
		assertFalse(!updatedObj.username().equals(CHANGED_USERNAME));

		// Page<SaleOrder> page = _saleOrderSql.page(0, 10);
		// assertFalse(page.size() <= 0);

		_saleOrderSql.deleteById(retrievedObj.saleOrderId());
		SaleOrder deletedObj = _saleOrderSql.findOneById(retrievedObj.saleOrderId());
		assertFalse(deletedObj != null);
	}

	private SaleOrder newSaleOrder() {
		Random random = new Random();
		Long id = random.nextLong();
		// SaleOrder saleOrder = new SaleOrder(new SaleOrderId(id), "book",
		// "This is
		// book!");
		SaleOrder saleOrder = saleOrderFactory.saleOrder(new SaleOrderId(id), USERNAME, new Date());
		return saleOrder;
	}
}
