package com.mystore.shop.port.adapter.persistence.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.mystore.common.persistence.jdbc.JdbcEntityDao;
import com.mystore.shop.domain.model.order.SaleOrderItemBase;
import com.mystore.shop.domain.model.order.SaleOrderItemId;

@Component
public class SaleOrderItemBaseSql extends JdbcEntityDao<SaleOrderItemBase, SaleOrderItemId> {

	@Override
	protected SaleOrderItemBase produceObject(SaleOrderItemId key) {
		SaleOrderItemBase saleOrderItemBase = produceObject();
		saleOrderItemBase.setSaleOrderItemId(key);
		return saleOrderItemBase;
	}

	@Override
	protected SaleOrderItemId fetchKey(SaleOrderItemBase object) {
		return object.getSaleOrderItemId();
	}

	@Override
	protected void init() {
		this.table = new SaleOrderItemTable();
	}

	@Autowired
	@Override
	protected void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	protected SaleOrderItemBase produceObject() {
		return new SaleOrderItemBase();
	}

}
