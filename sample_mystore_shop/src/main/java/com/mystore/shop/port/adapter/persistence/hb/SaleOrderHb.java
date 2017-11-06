package com.mystore.shop.port.adapter.persistence.hb;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.mystore.shop.domain.model.order.SaleOrderId;
import com.mystore.shop.domain.model.order.SaleOrderModel;

public interface SaleOrderHb extends CrudRepository<SaleOrderModel, SaleOrderId> {

	@Query("select max(s.saleOrderId.id) from SaleOrderModel s")
	public Long findMaxId();
}
