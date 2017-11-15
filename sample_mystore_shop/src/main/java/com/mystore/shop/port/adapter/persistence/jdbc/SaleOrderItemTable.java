package com.mystore.shop.port.adapter.persistence.jdbc;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mystore.common.persistence.Table;
import com.mystore.shop.domain.model.order.SaleOrderId;
import com.mystore.shop.domain.model.order.SaleOrderItemBase;
import com.mystore.shop.domain.model.order.SaleOrderItemId;
import com.mystore.shop.domain.model.productitem.ProductItemId;

public class SaleOrderItemTable extends Table<SaleOrderItemBase> {

	public static final String TABLENAME = "saleorderitem";
	public static final String ID = "saleorderitemid";
	public static final String SALEORDERID = "saleorderid";
	public static final String PRODUCTITEMID = "productitemid";
	public static final String QUANTITY = "quantity";
	public static final String UNITPRICE = "unitprice";
	public static final String TOTAL = "total";

	@Override
	protected void init() {
		this.setTableName(TABLENAME);

		this.add(ID,
				(PreparedStatement ps, int index, SaleOrderItemBase saleOrderItem) -> ps.setLong(index,
						saleOrderItem.getSaleOrderItemId().getId()),
				(SaleOrderItemBase saleOrderItem, ResultSet rs) -> saleOrderItem
						.setSaleOrderItemId(new SaleOrderItemId(rs.getLong(ID))));
		this.setPrimaryKay(ID);

		this.add(SALEORDERID,
				(PreparedStatement ps, int index, SaleOrderItemBase saleOrderItem) -> ps.setLong(index,
						saleOrderItem.getSaleOrderId().getId()),
				(SaleOrderItemBase saleOrderItem, ResultSet rs) -> saleOrderItem
						.setSaleOrderId(new SaleOrderId(rs.getLong(SALEORDERID))));

		this.add(PRODUCTITEMID,
				(PreparedStatement ps, int index, SaleOrderItemBase saleOrderItem) -> ps.setLong(index,
						saleOrderItem.getProductItemId().getId()),
				(SaleOrderItemBase saleOrderItem, ResultSet rs) -> saleOrderItem
						.setProductItemId(new ProductItemId(rs.getLong(PRODUCTITEMID))));

		this.add(QUANTITY,
				(PreparedStatement ps, int index, SaleOrderItemBase saleOrderItem) -> ps.setInt(index,
						saleOrderItem.getQuantity()),
				(SaleOrderItemBase saleOrderItem, ResultSet rs) -> saleOrderItem.setQuantity(rs.getInt(QUANTITY)));

		this.add(UNITPRICE,
				(PreparedStatement ps, int index, SaleOrderItemBase saleOrderItem) -> ps.setBigDecimal(index,
						saleOrderItem.getUnitPrice()),
				(SaleOrderItemBase saleOrderItem, ResultSet rs) -> saleOrderItem
						.setUnitPrice(rs.getBigDecimal(UNITPRICE)));

		this.add(TOTAL,
				(PreparedStatement ps, int index, SaleOrderItemBase saleOrderItem) -> ps.setBigDecimal(index,
						saleOrderItem.getTotal()),
				(SaleOrderItemBase saleOrderItem, ResultSet rs) -> saleOrderItem.setTotal(rs.getBigDecimal(TOTAL)));

	}

}
