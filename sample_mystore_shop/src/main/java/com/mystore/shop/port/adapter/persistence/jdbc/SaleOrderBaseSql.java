package com.mystore.shop.port.adapter.persistence.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.mystore.common.persistence.jdbc.AggregateRootJdbcDao;
import com.mystore.shop.domain.model.order.SaleOrderBase;
import com.mystore.shop.domain.model.order.SaleOrderId;

public class SaleOrderBaseSql extends AggregateRootJdbcDao<SaleOrderBase, SaleOrderId> {

	@Override
	protected SaleOrderBase produceObject(SaleOrderId key) {
		SaleOrderBase saleOrderBase = produceObject();
		saleOrderBase.setSaleOrderId(key);
		return saleOrderBase;
	}

	@Override
	protected SaleOrderId fetchId(SaleOrderBase object) {
		if (object != null)
			return object.getSaleOrderId();
		return null;
	}

	@Override
	protected void initTable() {
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

	@Override
	protected void initClass() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void initMetaFactory() {
		// TODO Auto-generated method stub
		
	}

}
