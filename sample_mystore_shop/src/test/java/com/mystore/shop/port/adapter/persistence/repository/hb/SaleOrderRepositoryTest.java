package com.mystore.shop.port.adapter.persistence.repository.hb;

import java.util.Date;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mystore.shop.domain.model.order.SaleOrder;
import com.mystore.shop.domain.model.order.SaleOrderFactory;
import com.mystore.shop.port.adapter.persistence.hb.SaleOrderRepositoryHb;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SaleOrderRepositoryTest {

	@Autowired
	private SaleOrderRepositoryHb saleOrderRepositoryHb;

	@Autowired
	private SaleOrderFactory saleOrderFactory;

	@Test
	public void covered() {

		for(int i=0;i<1000;i++){
			SaleOrder newSaleOrder = newSaleOrder();
			saleOrderRepositoryHb.create(newSaleOrder);
		}
		// SaleOrder newSaleOrder = newSaleOrder();
		// saleOrderRepositoryHb.create(newSaleOrder);

	}

	private SaleOrder newSaleOrder() {
		// Random random = new Random();
		// Long id = random.nextLong();
		// SaleOrderId saleOrderId = null;
		// SaleOrderId saleOrderId = new SaleOrderId();
		// SaleOrderId saleOrderId = new SaleOrderId(id);
		// SaleOrderId saleOrderId = new SaleOrderId(3L);
		// SaleOrderId saleOrderId = saleOrderRepositoryHb.nextId();
		return saleOrderFactory.saleOrder("zhangshan_" + new Random().nextInt(), new Date());

		// return saleOrderFactory.saleOrder("zhangshan_" + randomId, new
		// Date());

	}
}
