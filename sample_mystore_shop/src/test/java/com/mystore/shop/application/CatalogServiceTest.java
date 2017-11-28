package com.mystore.shop.application;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CatalogServiceTest {

	final long SALEORDERID = 3999078814328962672L;
	
	@Autowired
	private CatalogService catalogService;
	
	@Test
	public void requestPeyout(){
		
		catalogService.requestPeyout(SALEORDERID);
	}
}
