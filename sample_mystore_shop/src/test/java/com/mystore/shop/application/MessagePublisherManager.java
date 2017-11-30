package com.mystore.shop.application;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mystore.common.message.MessagePublisher;

@Component
public class MessagePublisherManager {

	@Autowired
	private MessagePublisher _messagePublisher;

	@PostConstruct
	private void init() {
		MessagePublisherTimer timer = new MessagePublisherTimer();
		new Thread(timer).start();
	}

	private class MessagePublisherTimer implements Runnable {

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
