package com.mystore.shop.application;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.mystore.common.domain.model.DomainEvent;
import com.mystore.common.event.EventStore;
import com.mystore.common.event.StoredEvent;
import com.mystore.common.message.DomainMessage;

@Component
public class MessagePublisherKafka implements MessagePulisher {

	private EventStore eventStore;

	@Override
	public void publishMessages() {
		System.out.println("Publish Messages!");
		List<StoredEvent> storedEventList = eventStore.allStoredEventsSince(0);
		// List<DomainMessage> domainMessageList = new
		// ArrayList<DomainMessage>(storedEventList.size());

		for (StoredEvent storedEvent : storedEventList) {
			DomainEvent domainEvent = storedEvent.toDomainEvent();
			DomainMessage domainMessage = new DomainMessage(storedEvent.eventId(), domainEvent);
			// domainMessageList.add(domainMessage);
			
		}

	}

}
