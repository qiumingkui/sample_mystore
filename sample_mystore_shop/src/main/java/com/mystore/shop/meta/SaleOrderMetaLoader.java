package com.mystore.shop.meta;

import com.mystore.common.meta.ClassMeta;
import com.mystore.common.meta.MetaLoader;
import com.mystore.common.meta.RegisterCenter;
import com.mystore.common.meta.domain.AggregateRootMeta;
import com.mystore.shop.domain.model.order.SaleOrder;

public class SaleOrderMetaLoader implements MetaLoader<SaleOrder> {

	@Override
	public void loading(RegisterCenter registerCenter) {
		registerCenter.getAggregateRootMetaRegister()
				.register(new AggregateRootMeta<SaleOrder>(SaleOrder.class.getName(), SaleOrder.SALEORDERID));

		registerCenter.getClassMetaRegister().register(new ClassMeta<SaleOrder>(SaleOrder.class));

		registerCenter.getTableRegister().register(new SaleOrderTable());
	}

}
