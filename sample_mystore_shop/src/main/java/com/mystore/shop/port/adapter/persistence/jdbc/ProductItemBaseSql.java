package com.mystore.shop.port.adapter.persistence.jdbc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.mystore.common.persistence.Column;
import com.mystore.common.persistence.jdbc.AggregateRootJdbcDao;
import com.mystore.shop.domain.model.product.ProductId;
import com.mystore.shop.domain.model.productitem.ProductItemBase;
import com.mystore.shop.domain.model.productitem.ProductItemId;

@Component
public class ProductItemBaseSql extends AggregateRootJdbcDao<ProductItemBase, ProductItemId> {

	public List<ProductItemBase> findAllByProductId(ProductId productId) {
		List<ProductItemId> productItemIds = findAllIdByProductId(productId);
		List<ProductItemBase> productItemBases = this.findAll(productItemIds);
		return productItemBases;
	}

	public List<ProductItemId> findAllIdByProductId(ProductId productId) {
		Collection<Column<ProductItemBase>> rsColumns = new ArrayList<Column<ProductItemBase>>();
		rsColumns.add(table.getPrimaryKey());

		String SQL = "SELECT #{pk} FROM #{table} WHERE #{productid}=?";
		SQL = sqlSetting(SQL, "table", table.getTableName());
		SQL = sqlSetting(SQL, "pk", table.getPrimaryKey().getColumnName());
		SQL = sqlSetting(SQL, "productid", table.getColumn(ProductItemTable.PRODUCTID).getColumnName());

		List<ProductItemBase> objectWithKeyList = jdbcTemplate.query(SQL, new Object[] { productId.getId() },
				provideRowMapper(rsColumns));
		return fetchIdList(objectWithKeyList);
	}

	@Override
	protected void initTable() {
		this.table = new ProductItemTable();
	}

	@Override
	@Autowired
	protected void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
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
	protected ProductItemId fetchId(ProductItemBase object) {
		ProductItemId productItemId = object.getProductItemId();
		return productItemId;
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
