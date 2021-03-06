package com.mystore.shop.meta;

import com.mystore.common.persistence.Table;
import com.mystore.shop.domain.model.order.SaleOrderId;
import com.mystore.shop.domain.model.order.SaleOrderItem;
import com.mystore.shop.domain.model.order.SaleOrderItemId;
import com.mystore.shop.domain.model.productitem.ProductItemId;

public class SaleOrderItemTable extends Table<SaleOrderItem> {

	public static final String TABLENAME = "saleorderitem";
	public static final String ID = "saleorderitemid";
	public static final String SALEORDERID = "saleorderid";
	public static final String PRODUCTITEMID = "productitemid";
	public static final String QUANTITY = "quantity";
	public static final String UNITPRICE = "unitprice";
	public static final String TOTAL = "total";

	@Override
	protected void init() {
		this.setClassName(SaleOrderItem.class.getName());
		this.setTableName(TABLENAME);

		this.add(ID, SaleOrderItem.SALEORDERITEMID+"."+SaleOrderItemId.ID);
		this.setPrimaryKay(ID);

		this.add(SALEORDERID, SaleOrderItem.SALEORDERID+"."+SaleOrderId.ID);

		this.add(PRODUCTITEMID, SaleOrderItem.PRODUCTITEMID+"."+ProductItemId.ID);

		this.add(QUANTITY, SaleOrderItem.QUANTITY);

		this.add(UNITPRICE, SaleOrderItem.UNITPRICE);

		this.add(TOTAL, SaleOrderItem.TOTAL);

	}

}
