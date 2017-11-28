package com.mystore.common.message;

import java.io.Serializable;
import java.util.Date;

import com.mystore.common.domain.model.DomainEvent;

public class DomainMessage implements Serializable {

	private static final long serialVersionUID = 1L;

	private DomainEvent event;
	private long domainMessageId;
	private Date occurredOn;
	private String typeName;
	private int version;

	public DomainMessage(long domainMessageId, DomainEvent domainEvent) {

		this.setEvent(domainEvent);
		this.setDomainMessageId(domainMessageId);
		this.setOccurredOn(domainEvent.getOccurredOn());
		this.setTypeName(domainEvent.getClass().getName());
		this.setVersion(domainEvent.getEventVersion());
	}

	@SuppressWarnings("unchecked")
	public <T extends DomainEvent> T event() {
		return (T) this.event;
	}

	public long domainMessageId() {
		return this.domainMessageId;
	}

	public Date occurredOn() {
		return this.occurredOn;
	}

	public String typeName() {
		return this.typeName;
	}

	public int version() {
		return version;
	}

	@Override
	public boolean equals(Object object) {
		boolean equalObjects = false;

		if (object != null && this.getClass() == object.getClass()) {
			DomainMessage typedObject = (DomainMessage) object;
			equalObjects = this.domainMessageId() == typedObject.domainMessageId();
		}

		return equalObjects;
	}

	@Override
	public int hashCode() {
		int hashCodeValue = +(3017 * 197) + (int) this.domainMessageId();

		return hashCodeValue;
	}

	@Override
	public String toString() {
		return "DomainMessage [event=" + event + ", domainMessageId=" + domainMessageId + ", occurredOn=" + occurredOn
				+ ", typeName=" + typeName + ", version=" + version + "]";
	}

	protected void setEvent(DomainEvent event) {
		// this.assertArgumentNotNull(anEvent, "The event is required.");

		this.event = event;
	}

	protected void setDomainMessageId(long domainMessageId) {
		this.domainMessageId = domainMessageId;
	}

	protected void setOccurredOn(Date occurredOn) {
		this.occurredOn = occurredOn;
	}

	protected void setTypeName(String typeName) {
		// this.assertArgumentNotEmpty(aTypeName, "The type name is required.");
		// this.assertArgumentLength(aTypeName, 100, "The type name must be 100
		// characters or less.");

		this.typeName = typeName;
	}

	private void setVersion(int version) {
		this.version = version;
	}
}
