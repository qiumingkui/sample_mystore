package com.mystore.shop.port.adapter.persistence.hb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mystore.shop.domain.model.order.SaleOrder;
import com.mystore.shop.domain.model.order.SaleOrderRepository;

@Component
public class SaleOrderRepositoryHb implements SaleOrderRepository {

	@Autowired
	private SaleOrderHb saleOrderHb;

	@Override
	public SaleOrder get(Long saleOrderId) {
		SaleOrder saleOrder = saleOrderHb.findOne(saleOrderId);
		return saleOrder;
	}

	@Override
	public void create(SaleOrder saleOrder) {
		saleOrderHb.save(saleOrder);
	}

	@Override
	public void update(SaleOrder saleOrder) {
		saleOrderHb.save(saleOrder);
	}

	@Override
	public void delete(Long saleOrderId) {
		saleOrderHb.delete(saleOrderId);
	}

}
