package com.mystore.shop.port.adapter.persistence.repository.hb;

import static org.junit.Assert.assertFalse;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.mystore.shop.domain.model.order.SaleOrder;
import com.mystore.shop.domain.model.order.SaleOrderFactory;
import com.mystore.shop.domain.model.order.SaleOrderItem;
import com.mystore.shop.domain.model.productitem.ProductItemId;
import com.mystore.shop.port.adapter.persistence.hb.SaleOrderRepositoryHb;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SaleOrderRepositoryTest {

	@Autowired
	private SaleOrderRepositoryHb _saleOrderRepository;

	@Autowired
	private SaleOrderFactory saleOrderFactory;

	@Test
	// @Transactional(propagation=Propagation.SUPPORTS)
	public void covered() {

		// for(int i=0;i<1000;i++){
		// SaleOrder newSaleOrder = newSaleOrder();
		// saleOrderRepositoryHb.create(newSaleOrder);
		// }
		SaleOrder newObj = newSaleOrder();
		_saleOrderRepository.create(newObj);
		SaleOrder retrievedObj = _saleOrderRepository.get(newObj.saleOrderId());
		assertFalse(retrievedObj == null);

		String updateUsername = "Lisi_" + new Random().nextInt();
		retrievedObj.changeUsername(updateUsername);
		SaleOrderItem saleOrderItem0 = new SaleOrderItem(2, new BigDecimal(3), new BigDecimal(4),
				new ProductItemId(1L));
		retrievedObj.addSaleOrderItem(saleOrderItem0);
		SaleOrderItem saleOrderItem1 = new SaleOrderItem(2, new BigDecimal(3), new BigDecimal(4),
				new ProductItemId(2L));
		retrievedObj.addSaleOrderItem(saleOrderItem1);
		_saleOrderRepository.update(retrievedObj);
		SaleOrder updatedObj = _saleOrderRepository.get(retrievedObj.saleOrderId());
		assertFalse(updatedObj == null);
		assertFalse(!updatedObj.username().equals(updateUsername));

		_saleOrderRepository.delete(updatedObj.saleOrderId());
		retrievedObj = _saleOrderRepository.get(newObj.saleOrderId());
		assertFalse(retrievedObj != null);
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
