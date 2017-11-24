package com.mystore.shop.domain.model.order.event;

import java.io.Serializable;
import java.util.Date;

import com.mystore.common.domain.model.DomainEvent;
import com.mystore.shop.domain.model.order.SaleOrderId;

public class RequestPayoutEvent implements DomainEvent, Serializable {

	private static final long serialVersionUID = 1L;

	private SaleOrderId saleOrderId;

	private String userName;

	private int eventVersion;

	private Date occurredOn;

	public RequestPayoutEvent(SaleOrderId saleOrderId, String userName, int eventVersion, Date occurredOn) {
		super();
		this.saleOrderId = saleOrderId;
		this.userName = userName;
		this.eventVersion = eventVersion;
		this.occurredOn = occurredOn;
	}

	@Override
	public int getEventVersion() {
		return this.eventVersion;
	}

	@Override
	public Date getOccurredOn() {
		return this.occurredOn;
	}

}
