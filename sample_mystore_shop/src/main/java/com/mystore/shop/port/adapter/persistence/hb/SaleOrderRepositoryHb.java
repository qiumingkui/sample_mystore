//package com.mystore.shop.port.adapter.persistence.hb;
//
//import java.util.concurrent.atomic.AtomicLong;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import com.mystore.shop.domain.model.order.SaleOrder;
//import com.mystore.shop.domain.model.order.SaleOrderId;
//import com.mystore.shop.domain.model.order.SaleOrderModel;
//import com.mystore.shop.domain.model.order.SaleOrderRepository;
//
//@Component
//public class SaleOrderRepositoryHb implements SaleOrderRepository {
//	private static AtomicLong _SaleOrderSN = new AtomicLong(-1);
//	private static final Object _lock = new Object();
//
//	@Autowired
//	private SaleOrderHb saleOrderHb;
//
//	@Override
//	public SaleOrder get(SaleOrderId saleOrderId) {
//
//		SaleOrder saleOrder = saleOrderHb.findOne(saleOrderId);
//		return saleOrder;
//	}
//
//	@Override
//	public void create(SaleOrder saleOrder) {
//		saleOrderHb.save((SaleOrderModel) saleOrder);
//	}
//
//	@Override
//	public void update(SaleOrder saleOrder) {
//		saleOrderHb.save((SaleOrderModel) saleOrder);
//	}
//
//	@Override
//	public void delete(SaleOrderId saleOrderId) {
//		saleOrderHb.delete(saleOrderId);
//	}
//
//	// @Override
//	// public SaleOrderId nextId() {
//	// if (_SaleOrderSN.longValue() == -1) {
//	// synchronized (_lock) {
//	// if (_SaleOrderSN.longValue() == -1) {
//	// Long id = saleOrderHb.findMaxId();
//	// if (id == null || id <= 0) {
//	// _SaleOrderSN.set(0);
//	// } else {
//	// _SaleOrderSN.set(id);
//	// }
//	// }
//	// }
//	// }
//	// // 仅支持单服务器模式
//	// SaleOrderId saleOrderId = new SaleOrderId(_SaleOrderSN.addAndGet(1));
//	// return saleOrderId;
//	// }
//
//}
