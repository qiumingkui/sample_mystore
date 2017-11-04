package com.mystore.shop.port.adapter.persistence.hb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.mystore.shop.domain.model.order.SaleOrder;
import com.mystore.shop.domain.model.order.SaleOrderId;
import com.mystore.shop.domain.model.order.SaleOrderModel;
import com.mystore.shop.domain.model.order.SaleOrderRepository;

@Component
public class SaleOrderRepositoryHb implements SaleOrderRepository {

	@Autowired
	private SaleOrderHb saleOrderHb;

	@Override
	public SaleOrder get(SaleOrderId saleOrderId) {

		SaleOrder saleOrder = saleOrderHb.findOne(saleOrderId);
		return saleOrder;
	}

	@Override
	public void create(SaleOrder saleOrder) {
		// Assert.isTrue(!saleOrderHb.exists(saleOrder.saleOrderId()));
		saleOrderHb.save((SaleOrderModel) saleOrder);
	}

	@Override
	public void update(SaleOrder saleOrder) {
		saleOrderHb.save((SaleOrderModel) saleOrder);
	}

	@Override
	public void delete(SaleOrderId saleOrderId) {
		saleOrderHb.delete(saleOrderId);
	}

	@Override
	public SaleOrderId nextId() {
		Long id = saleOrderHb.findMaxId();
		if (id == null)
			id = 0L;
		SaleOrderId saleOrderId = new SaleOrderId(id + 1);
		return saleOrderId;
	}

}
