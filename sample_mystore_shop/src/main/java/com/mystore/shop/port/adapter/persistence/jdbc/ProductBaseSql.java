package com.mystore.shop.port.adapter.persistence.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.mystore.common.persistence.jdbc.AggregateRootJdbcDao;
import com.mystore.shop.domain.model.product.ProductBase;
import com.mystore.shop.domain.model.product.ProductId;

@Component
public class ProductBaseSql extends AggregateRootJdbcDao<ProductBase, ProductId> {

	@Override
	@Autowired
	protected void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	protected void initTable() {
		this.table=new ProductTable();
	}

	@Override
	protected ProductBase produceObject(ProductId key) {
		ProductBase productBase = produceObject();
		productBase.setProductId(key);
		return productBase;
	}

	@Override
	protected ProductBase produceObject() {
		ProductBase productBase = new ProductBase();
		return productBase;
	}

	@Override
	protected ProductId fetchId(ProductBase object) {
		ProductId productId = object.getProductId();
		return productId;
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
