package com.mystore.common.domain.model;

import java.io.Serializable;
import java.util.Date;

public class DomainEvent implements Serializable{

	private static final long serialVersionUID = 1L;

	private DomainEventId domainEventId ;
	
	private String domainEventName;
	
	private Date  occurrenceTime;

	public Date getOccurrenceTime() {
		return occurrenceTime;
	}

	public void setOccurrenceTime(Date occurrenceTime) {
		this.occurrenceTime = occurrenceTime;
	}

	public DomainEventId getDomainEventId() {
		return domainEventId;
	}

	public void setDomainEventId(DomainEventId domainEventId) {
		this.domainEventId = domainEventId;
	}

	public String getDomainEventName() {
		return domainEventName;
	}

	public void setDomainEventName(String domainEventName) {
		this.domainEventName = domainEventName;
	}

}
