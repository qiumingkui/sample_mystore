package com.mystore.shop.domain.model.order;

public interface SaleOrderRepository {

	public SaleOrder get(Long saleOrderId);

	public void create(SaleOrder saleOrder);

	public void update(SaleOrder saleOrder);

	public void delete(Long saleOrderId);

}
