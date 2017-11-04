package com.mystore.shop.port.adapter.persistence.hb;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.mystore.shop.domain.model.order.SaleOrder;
import com.mystore.shop.domain.model.order.SaleOrderId;

public interface SaleOrderHb extends CrudRepository<SaleOrder, SaleOrderId> {

	@Query("select max(s._saleOrderId._id) from SaleOrder s")
	public Long findMaxId();
}
