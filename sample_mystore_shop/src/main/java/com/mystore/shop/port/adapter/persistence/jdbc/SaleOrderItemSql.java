package com.mystore.shop.port.adapter.persistence.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.mystore.common.persistence.jdbc.AggregateRootJdbcDao;
import com.mystore.common.persistence.jdbc.SubEntityJdbcDao;
import com.mystore.shop.domain.model.order.SaleOrderId;
import com.mystore.shop.domain.model.order.SaleOrderItem;
import com.mystore.shop.domain.model.order.SaleOrderItemId;
import com.mystore.shop.meta.SaleOrderItemTable;
import com.mystore.shop.meta.SysMetaFactory;

@Component
public class SaleOrderItemSql extends SubEntityJdbcDao<SaleOrderItem, SaleOrderItemId, SaleOrderId> {

	// @Override
	// protected SaleOrderItem produceObject(SaleOrderItemId key) {
	// SaleOrderItem saleOrderItemBase = produceObject();
	// saleOrderItemBase.setSaleOrderItemId(key);
	// return saleOrderItemBase;
	// }

	// @Override
	// protected SaleOrderItemId fetchId(SaleOrderItem object) {
	// return object.getSaleOrderItemId();
	// }

	@Override
	protected void initTable() {
		this.table = new SaleOrderItemTable();
	}

	@Autowired
	protected void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	// @Override
	// protected SaleOrderItem produceObject() {
	// // TODO Auto-generated method stub
	// return null;
	// }

	@Override
	protected void initClass() {
		this.clazz = SaleOrderItem.class;
	}

	@Override
	protected void initMetaFactory() {
		this.metaFactory = SysMetaFactory.instance();

	}

}
