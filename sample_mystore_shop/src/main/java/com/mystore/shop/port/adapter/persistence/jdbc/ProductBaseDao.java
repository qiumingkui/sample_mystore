package com.mystore.shop.port.adapter.persistence.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.mystore.common.persistence.jdbc.JdbcCurdDao;
import com.mystore.shop.domain.model.product.ProductBase;
import com.mystore.shop.domain.model.product.ProductId;
import com.mystore.shop.domain.model.product.ProductTable;

@Component
public class ProductBaseDao extends JdbcCurdDao<ProductBase, ProductId> {

	public ProductBaseDao() {
		super();
		super.table = new ProductTable();
	}

	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
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
	protected ProductId fetchKey(ProductBase object) {
		ProductId productId = object.getProductId();
		return productId;
	}

}
