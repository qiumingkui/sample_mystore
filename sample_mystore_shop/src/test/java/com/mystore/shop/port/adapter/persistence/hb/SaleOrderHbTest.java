package com.mystore.shop.port.adapter.persistence.hb;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mystore.shop.domain.model.order.SaleOrder;
import com.mystore.shop.domain.model.order.SaleOrderFactory;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SaleOrderHbTest {

	@Autowired
	private SaleOrderHb saleOrderHb ;
	
	@Autowired
	private SaleOrderFactory saleOrderFactory;
	
	@Test
	public void covered(){
		
		SaleOrder newSaleOrder = newSaleOrder();
		saleOrderHb.save(newSaleOrder);
	}
	
	private SaleOrder newSaleOrder(){
		return saleOrderFactory.saleOrder("zhangshan", new Date());
	}
}
