package com.mystore.shop.port.adapter.persistence.jdbc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mystore.shop.domain.model.order.SaleOrder;
import com.mystore.shop.domain.model.order.SaleOrderId;
import com.mystore.shop.domain.model.order.SaleOrderRepository;


@Component
public class SaleOrderRepositorySql implements SaleOrderRepository {

	@Autowired
	private SaleOrderSql saleOrderSql;

	@Override
	public void create(SaleOrder saleOrder) {

		saleOrderSql.insert(saleOrder);
	}

	@Override
	public void update(SaleOrder saleOrder) {

		saleOrderSql.update(saleOrder);
	}

	@Override
	public void delete(SaleOrderId saleOrderId) {

		saleOrderSql.deleteById(saleOrderId);
	}

	@Override
	public SaleOrder get(SaleOrderId saleOrderId) {

		SaleOrder saleOrder = saleOrderSql.findOneById(saleOrderId);

		return saleOrder;
	}

	public List<SaleOrder> list() throws Exception {

		List<SaleOrder> saleOrderList = saleOrderSql.findAll();

		return saleOrderList;
	}

	// @Override
	// public Page<SaleOrder> page(int index, int size) {
	//
	// List<SaleOrder> saleOrders = saleOrderSql.page(index, size);
	// Page<SaleOrder> page = new Page<SaleOrder>(index, size, 0);
	// page.addAll(saleOrders);
	//
	// return page;
	// }

}
