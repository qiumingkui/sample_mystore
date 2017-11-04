package com.mystore.shop.port.adapter.persistence.hb;

import java.util.Date;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mystore.shop.domain.model.order.SaleOrder;
import com.mystore.shop.domain.model.order.SaleOrderFactory;
import com.mystore.shop.domain.model.order.SaleOrderId;
import com.mystore.shop.domain.model.order.SaleOrderRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SaleOrderRepositoryTest {

	@Autowired
	private SaleOrderRepositoryHb saleOrderRepository;

	@Autowired
	private SaleOrderFactory saleOrderFactory;

	@Test
	public void covered() {

		SaleOrder newSaleOrder = newSaleOrder();
		saleOrderRepository.create(newSaleOrder);
	}

	private SaleOrder newSaleOrder() {
		// Random random = new Random();
		// Long id = random.nextLong();
		// SaleOrderId saleOrderId = new SaleOrderId(id);
		// SaleOrderId saleOrderId = new SaleOrderId(3L);
		SaleOrderId saleOrderId = saleOrderRepository.nextId();
		return saleOrderFactory.saleOrder(saleOrderId, "zhangshan_" + saleOrderId.id(), new Date());

		// return saleOrderFactory.saleOrder("zhangshan_" + randomId, new
		// Date());

	}
}
