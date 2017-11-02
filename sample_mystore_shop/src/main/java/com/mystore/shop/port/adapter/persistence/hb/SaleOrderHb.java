package com.mystore.shop.port.adapter.persistence.hb;

import org.springframework.data.repository.CrudRepository;

import com.mystore.shop.domain.model.order.SaleOrder;


public interface SaleOrderHb extends CrudRepository<SaleOrder, Long> {

}
