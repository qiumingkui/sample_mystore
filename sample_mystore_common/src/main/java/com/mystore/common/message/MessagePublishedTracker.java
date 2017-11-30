package com.mystore.common.message;

import java.io.Serializable;

import com.mystore.common.utils.Assert;

public class MessagePublishedTracker implements Serializable {

	private static final long serialVersionUID = 1L;

	private int concurrencyVersion;
	private long mostRecentMessagePublishedId;
	private long messagePublishedTrackerId;
	private String typeName;

	public MessagePublishedTracker(String typeName) {
		this();

		this.setTypeName(typeName);
	}

	public void failWhenConcurrencyViolation(int version) {
		Assert.assertStateTrue(version == this.concurrencyVersion(),
				"Concurrency Violation: Stale data detected. Entity was already modified.");
	}

	public long mostRecentMessagePublishedId() {
		return this.mostRecentMessagePublishedId;
	}

	public void setMostRecentMessagePublishedId(long mostRecentMessagePublishedId) {
		this.mostRecentMessagePublishedId = mostRecentMessagePublishedId;
	}

	public long messagePublishedTrackerId() {
		return this.messagePublishedTrackerId;
	}

	public String typeName() {
		return this.typeName;
	}

	@Override
	public boolean equals(Object object) {
		boolean equalObjects = false;

		if (object != null && this.getClass() == object.getClass()) {
			MessagePublishedTracker typedObject = (MessagePublishedTracker) object;
			equalObjects = this.messagePublishedTrackerId() == typedObject.messagePublishedTrackerId()
					&& this.typeName().equals(typedObject.typeName())
					&& this.mostRecentMessagePublishedId() == typedObject.mostRecentMessagePublishedId();
		}

		return equalObjects;
	}

	@Override
	public int hashCode() {
		int hashCodeValue = +(11575 * 241) + (int) this.messagePublishedTrackerId()
				+ (int) this.mostRecentMessagePublishedId() + this.typeName().hashCode();

		return hashCodeValue;
	}

	@Override
	public String toString() {
		return "MessagePublishedTracker [mostRecentMessagePublishedId=" + mostRecentMessagePublishedId
				+ ", messagePublishedTrackerId=" + messagePublishedTrackerId + ", typeName=" + typeName + "]";
	}

	public MessagePublishedTracker() {
		super();
	}

	public int concurrencyVersion() {
		return this.concurrencyVersion;
	}

	public void setConcurrencyVersion(int concurrencyVersion) {
		this.concurrencyVersion = concurrencyVersion;
	}

	public void setMessagePublishedTrackerId(long messagePublishedTrackerId) {
		this.messagePublishedTrackerId = messagePublishedTrackerId;
	}

	public void setTypeName(String typeName) {
		Assert.assertArgumentNotEmpty(typeName, "The tracker type name is required.");
		Assert.assertArgumentLength(typeName, 100, "The tracker type name must be 100 characters or less.");

		this.typeName = typeName;
	}
}
