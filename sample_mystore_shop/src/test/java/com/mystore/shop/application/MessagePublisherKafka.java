package com.mystore.shop.application;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.mystore.common.domain.model.DomainEvent;
import com.mystore.common.event.EventStore;
import com.mystore.common.event.StoredEvent;
import com.mystore.common.message.DomainMessage;
import com.mystore.common.message.MessagePublishedTracker;
import com.mystore.common.message.MessagePublishedTrackerStore;
import com.mystore.common.message.MessagePublisher;

@Component
public class MessagePublisherKafka implements MessagePublisher {

	@Autowired
	private EventStore eventStore;

	@Autowired
	private MessagePublishedTrackerStore messagePublishedTrackerStore;

	@Override
	public void publishMessages() {
		if (messagePublishedTrackerStore == null)
			return;
		MessagePublishedTracker messagePublishedTracker = messagePublishedTrackerStore.messagePublishedTracker();
		List<DomainMessage> messages = listUnpublishedMessages(messagePublishedTracker.mostRecentMessagePublishedId());
		for (DomainMessage domainMessage : messages) {
			//
			System.out.println("Publish Messages:" + domainMessage.domainMessageId());
		}
		refreshTracher(messagePublishedTracker, messages);
		messagePublishedTrackerStore.trackMostRecentPublishedMessage(messagePublishedTracker);
	}

	private void refreshTracher(MessagePublishedTracker messagePublishedTracker, List<DomainMessage> messages) {
		int lastIndex = messages.size() - 1;
		if (lastIndex >= 0) {
			long mostRecentMessagePublishedId = messages.get(lastIndex).domainMessageId();
			messagePublishedTracker.setMostRecentMessagePublishedId(mostRecentMessagePublishedId);
		}
	}

	private List<DomainMessage> listUnpublishedMessages(long mostRecentPublishedMessageId) {
		List<StoredEvent> storedEvents = this.eventStore.allStoredEventsSince(mostRecentPublishedMessageId);

		List<DomainMessage> messages = this.messagesFrom(storedEvents);

		return messages;
	}

	private List<DomainMessage> messagesFrom(List<StoredEvent> storedEvents) {
		List<DomainMessage> messages = new ArrayList<DomainMessage>(storedEvents.size());

		for (StoredEvent storedEvent : storedEvents) {
			DomainEvent domainEvent = storedEvent.toDomainEvent();

			DomainMessage message = new DomainMessage(storedEvent.eventId(), domainEvent);

			messages.add(message);
		}

		return messages;
	}

}
