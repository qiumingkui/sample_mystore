package com.mystore.shop.port.adapter.persistence.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.mystore.common.persistence.jdbc.AggregateRootJdbcDao;
import com.mystore.shop.domain.model.order.SaleOrderItemBase;
import com.mystore.shop.domain.model.order.SaleOrderItemId;

public class SaleOrderItemBaseSql extends AggregateRootJdbcDao<SaleOrderItemBase, SaleOrderItemId> {

	@Override
	protected SaleOrderItemBase produceObject(SaleOrderItemId key) {
		SaleOrderItemBase saleOrderItemBase = produceObject();
		saleOrderItemBase.setSaleOrderItemId(key);
		return saleOrderItemBase;
	}

	@Override
	protected SaleOrderItemId fetchId(SaleOrderItemBase object) {
		return object.getSaleOrderItemId();
	}

	@Override
	protected void initTable() {
		this.table = new SaleOrderItemTable();
	}

	@Autowired
	@Override
	protected void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	protected SaleOrderItemBase produceObject() {
		// TODO Auto-generated method stub
		return null;
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
