package com.mystore.shop.port.adapter.persistence;

import java.util.Date;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mystore.shop.domain.model.order.SaleOrder;
import com.mystore.shop.domain.model.order.SaleOrderFactory;
import com.mystore.shop.domain.model.order.SaleOrderRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SaleOrderRepositoryTest {

	@Autowired
	private SaleOrderRepository saleOrderRepository ;
	
	@Autowired
	private SaleOrderFactory saleOrderFactory;
	
	@Test
	public void covered(){
		
		SaleOrder newSaleOrder = newSaleOrder();
		saleOrderRepository.create(newSaleOrder);
	}
	
	private SaleOrder newSaleOrder(){
		Random random = new Random();
		return saleOrderFactory.saleOrder("zhangshan_"+random.nextInt(), new Date());
	}
}
