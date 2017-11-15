package com.mystore.shop.port.adapter.persistence.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.mystore.common.persistence.jdbc.JdbcEntityDao;
import com.mystore.shop.domain.model.order.SaleOrderBase;
import com.mystore.shop.domain.model.order.SaleOrderId;

@Component
public class SaleOrderBaseSql extends JdbcEntityDao<SaleOrderBase, SaleOrderId> {

	@Override
	protected SaleOrderBase produceObject(SaleOrderId key) {
		SaleOrderBase saleOrderBase = produceObject();
		saleOrderBase.setSaleOrderId(key);
		return saleOrderBase;
	}

	@Override
	protected SaleOrderId fetchKey(SaleOrderBase object) {
		if (object != null)
			return object.getSaleOrderId();
		return null;
	}

	@Override
	protected void init() {
		this.table = new SaleOrderTable();
	}

	@Autowired
	@Override
	protected void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	protected SaleOrderBase produceObject() {
		SaleOrderBase saleOrderBase = new SaleOrderBase();
		return saleOrderBase;
	}

}
