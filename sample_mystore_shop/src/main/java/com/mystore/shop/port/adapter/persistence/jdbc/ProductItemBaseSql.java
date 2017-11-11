package com.mystore.shop.port.adapter.persistence.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.mystore.common.persistence.jdbc.JdbcCurdDao;
import com.mystore.shop.domain.model.productitem.ProductItemBase;
import com.mystore.shop.domain.model.productitem.ProductItemId;
import com.mystore.shop.domain.model.productitem.ProductItemTable;

@Component
public class ProductItemBaseSql extends JdbcCurdDao<ProductItemBase, ProductItemId> {

	@Override
	protected void init() {
		this.table= new ProductItemTable();
	}

	@Autowired
	protected void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate=jdbcTemplate;
	}

	@Override
	protected ProductItemBase produceObject(ProductItemId key) {
		ProductItemBase productItemBase = produceObject();
		productItemBase.setProductItemId(key);
		return productItemBase;
	}

	@Override
	protected ProductItemBase produceObject() {
		ProductItemBase productItemBase = new ProductItemBase();
		return productItemBase;
	}

	@Override
	protected ProductItemId fetchKey(ProductItemBase object) {
		ProductItemId productItemId = object.getProductItemId();
		return productItemId;
	}

}
