package com.mystore.shop.domain.model.order;

import java.util.List;

public interface SaleOrderRepository {

	public SaleOrder get(int saleOrderId);

	public void create(SaleOrder saleOrder);

	public void update(SaleOrder saleOrder);

	public void delete(int saleOrderId);

	public List<SaleOrder> getSaleOrderList();

}
