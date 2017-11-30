package com.mystore.common.message;

public interface MessagePublishedTrackerStore {

	public MessagePublishedTracker messagePublishedTracker();

	public MessagePublishedTracker messagePublishedTracker(String typeName);

	public void trackMostRecentPublishedMessage(MessagePublishedTracker messagePublishedTrackers);

	public String getTypeName();
}
