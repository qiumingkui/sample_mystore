package com.mystore.shop.domain.model.productitem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mystore.common.persistence.Table;
import com.mystore.shop.domain.model.product.ProductId;

public class ProductItemTable extends Table<ProductItemBase> {
	private static final long serialVersionUID = 1L;
	
	public static final String ID = "id";
	public static final String PRODUCTID = "productid";
	public static final String LISTPRICE = "listprice";
	public static final String UNITCOST = "unitcost";
	public static final String QUANTITY = "quantity";

	@Override
	protected void init() {
		try {
			setName("productitem");

			add(ID, (PreparedStatement ps, int index, ProductItemBase productItemBase) -> ps.setLong(index,
					productItemBase.getProductItemId().getId()),
					(ProductItemBase productItemBase, ResultSet rs) -> productItemBase
							.setProductItemId(new ProductItemId(rs.getLong(ID))));
			get(ID).setPrimaryKay();
			
			add(PRODUCTID,
					(PreparedStatement ps, int index, ProductItemBase productItemBase) -> ps.setLong(index,
							productItemBase.getProductId().getId()),
					(ProductItemBase productItemBase, ResultSet rs) -> productItemBase
							.setProductId(new ProductId(rs.getLong(PRODUCTID))));
			
			add(LISTPRICE,
					(PreparedStatement ps, int index, ProductItemBase productItemBase) -> ps.setBigDecimal(index,
							productItemBase.getListPrice()),
					(ProductItemBase productItemBase, ResultSet rs) -> productItemBase
							.setListPrice(rs.getBigDecimal(LISTPRICE)));
			
			add(UNITCOST,
					(PreparedStatement ps, int index, ProductItemBase productItemBase) -> ps.setBigDecimal(index,
							productItemBase.getUnitCost()),
					(ProductItemBase productItemBase, ResultSet rs) -> productItemBase
							.setUnitCost(rs.getBigDecimal(UNITCOST)));
			
			add(QUANTITY,
					(PreparedStatement ps, int index, ProductItemBase productItemBase) -> ps.setInt(index,
							productItemBase.getQuantity()),
					(ProductItemBase productItemBase, ResultSet rs) -> productItemBase
							.setQuantity(rs.getInt(QUANTITY)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
