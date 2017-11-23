package com.mystore.shop.meta;

import com.mystore.common.meta.ClassMeta;
import com.mystore.common.meta.MetaLoader;
import com.mystore.common.meta.RegisterCenter;
import com.mystore.common.meta.domain.SubEntityMeta;
import com.mystore.shop.domain.model.order.SaleOrderItem;

public class SaleOrderItemMetaLoader implements MetaLoader<SaleOrderItem> {

	@Override
	public void loading(RegisterCenter registerCenter) {
		registerCenter.getSubEntityMetaRegister().register(new SubEntityMeta<SaleOrderItem>(
				SaleOrderItem.class.getName(), SaleOrderItem.SALEORDERITEMID, SaleOrderItem.SALEORDERID));

		registerCenter.getClassMetaRegister().register(new ClassMeta<SaleOrderItem>(SaleOrderItem.class));

		registerCenter.getTableRegister().register(new SaleOrderItemTable());
	}

}