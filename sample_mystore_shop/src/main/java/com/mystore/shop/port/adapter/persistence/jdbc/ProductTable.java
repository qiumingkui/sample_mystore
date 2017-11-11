package com.mystore.shop.port.adapter.persistence.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mystore.common.persistence.Column;
import com.mystore.common.persistence.Table;
import com.mystore.shop.domain.model.product.ProductBase;
import com.mystore.shop.domain.model.product.ProductId;

public class ProductTable extends Table<ProductBase> {

	private static final long serialVersionUID = 1L;

	public static final String TABLENAME = "product";
	public static final String ID = "id";
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";

	@Override
	protected void init() {
		try {
			setName(TABLENAME);

			add(ID, (PreparedStatement ps, int index, ProductBase product) -> ps.setLong(index,
					product.getProductId().getId()),
					(ProductBase product, ResultSet rs) -> product.setProductId(new ProductId(rs.getLong(ID))));
			this.get(ID).setPrimaryKay();

			add(NAME, (PreparedStatement ps, int index, ProductBase product) -> ps.setString(index, product.getName()),
					(ProductBase product, ResultSet rs) -> product.setName(rs.getString(NAME)));

			add(DESCRIPTION,
					(PreparedStatement ps, int index, ProductBase product) -> ps.setString(index,
							product.getDescription()),
					(ProductBase product, ResultSet rs) -> product.setDescription(rs.getString(DESCRIPTION)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
