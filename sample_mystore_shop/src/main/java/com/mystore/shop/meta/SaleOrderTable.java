package com.mystore.shop.meta;

import com.mystore.common.persistence.Table;
import com.mystore.shop.domain.model.order.SaleOrder;

public class SaleOrderTable extends Table<SaleOrder> {

	public static final String TABLENAME = "saleorder";
	public static final String ID = "saleorderid";
	public static final String USERNAME = "username";
	public static final String ORDERDATE = "orderdate";

	@Override
	protected void init() {
		this.setTableName(TABLENAME);

		this.add(ID, SaleOrder.SALEORDERID);
		this.setPrimaryKay(ID);

		this.add(USERNAME, SaleOrder.USERNAME);

		this.add(ORDERDATE, SaleOrder.ORDERDATE);

	}

}
