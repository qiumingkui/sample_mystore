package com.mystore.shop.domain.model.order;

public interface SaleOrderRepository {

	public SaleOrder get(SaleOrderId saleOrderId);

	public void create(SaleOrder saleOrder);

	public void update(SaleOrder saleOrder);

	public void delete(SaleOrderId saleOrderId);
	
	public SaleOrderId nextId();

}
