package com.mystore.shop.port.adapter.persistence.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.mystore.common.persistence.jdbc.AggregateRootJdbcDao;
import com.mystore.shop.domain.model.order.SaleOrder;
import com.mystore.shop.domain.model.order.SaleOrderId;
import com.mystore.shop.meta.SaleOrderTable;
import com.mystore.shop.meta.SysMetaFactory;

@Component
public class SaleOrderSql extends AggregateRootJdbcDao<SaleOrder, SaleOrderId> {

	// @Override
	// protected SaleOrder produceObject(SaleOrderId key) {
	// SaleOrder saleOrderBase = produceObject();
	// saleOrderBase.setSaleOrderId(key);
	// return saleOrderBase;
	// }
	//
	// @Override
	// protected SaleOrderId fetchId(SaleOrder object) {
	// if (object != null)
	// return object.getSaleOrderId();
	// return null;
	// }

	@Override
	protected void initTable() {
		this.table = new SaleOrderTable();
	}

	@Autowired
	@Override
	protected void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

//	@Override
//	protected SaleOrder produceObject() {
//		SaleOrder saleOrderBase = new SaleOrder();
//		return saleOrderBase;
//	}

	@Override
	protected void initClass() {
		this.clazz = SaleOrder.class;
	}

	@Override
	protected void initMetaFactory() {
		this.metaFactory = SysMetaFactory.instance();

	}

}
