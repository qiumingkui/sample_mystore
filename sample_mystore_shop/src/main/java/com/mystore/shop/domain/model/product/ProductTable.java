package com.mystore.shop.domain.model.product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mystore.common.persistence.Column;
import com.mystore.common.persistence.Table;

public class ProductTable extends Table<ProductBase> {

	private static final long serialVersionUID = 1L;

	@Override
	protected void init() {
		try {
			setName("product");

			Column<ProductBase> id = add("id",
					(PreparedStatement ps, int index, ProductBase product) -> ps.setLong(index,
							product.getProductId().getId()),
					(ProductBase product, ResultSet rs) -> product.setProductId(new ProductId(rs.getLong("id"))));
			id.setPrimaryKay();

			Column<ProductBase> name = add("name",
					(PreparedStatement ps, int index, ProductBase product) -> ps.setString(index, product.getName()),
					(ProductBase product, ResultSet rs) -> product.setName(rs.getString("name")));

			Column<ProductBase> description = add("description",
					(PreparedStatement ps, int index, ProductBase product) -> ps.setString(index,
							product.getDescription()),
					(ProductBase product, ResultSet rs) -> product.setDescription(rs.getString("description")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
