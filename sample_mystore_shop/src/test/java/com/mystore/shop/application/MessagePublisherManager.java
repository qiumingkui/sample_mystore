package com.mystore.shop.application;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessagePublisherManager {

	@Autowired
	private MessagePulisher _messagePublisher;

	@PostConstruct
	private void init() {
		MessagePublisherTimer timer = new MessagePublisherTimer();
		new Thread(timer).start();
	}

	private class MessagePublisherTimer implements Runnable {

		// private MessagePulisher messagePublisher;

		// private MessagePublisherTimer(MessagePulisher messagePublisher) {
		// super();
		// _messagePublisher = messagePublisher;
		// }

		@Override
		public void run() {
			while (true) {
				_messagePublisher.publishMessages();
				try {
					Thread.sleep(100L);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}
}
