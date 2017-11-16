package com.mystore.shop.port.adapter.persistence.jdbc;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mystore.common.persistence.Table;
import com.mystore.shop.domain.model.order.SaleOrderBase;
import com.mystore.shop.domain.model.order.SaleOrderId;

public class SaleOrderTable extends Table<SaleOrderBase> {

	public static final String TABLENAME = "saleorder";
	public static final String ID = "saleorderid";
	public static final String USERNAME = "username";
	public static final String ORDERDATE = "orderdate";

	@Override
	protected void init() {
		this.setTableName(TABLENAME);

		this.add(ID,
				(PreparedStatement ps, int index, SaleOrderBase saleOrder) -> ps.setLong(index,
						saleOrder.getSaleOrderId().getId()),
				(SaleOrderBase saleOrder, ResultSet rs) -> saleOrder.setSaleOrderId(new SaleOrderId(rs.getLong(ID))));
		this.setPrimaryKay(ID);

		this.add(USERNAME,
				(PreparedStatement ps, int index, SaleOrderBase saleOrder) -> ps.setString(index,
						saleOrder.getUsername()),
				(SaleOrderBase saleOrder, ResultSet rs) -> saleOrder.setUsername(rs.getString(USERNAME)));

		this.add(ORDERDATE,
				(PreparedStatement ps, int index, SaleOrderBase saleOrder) -> ps.setDate(index,
						new Date(saleOrder.getOrderDate().getTime())),
				(SaleOrderBase saleOrder, ResultSet rs) -> saleOrder.setOrderDate(rs.getDate(ORDERDATE)));

	}

}
