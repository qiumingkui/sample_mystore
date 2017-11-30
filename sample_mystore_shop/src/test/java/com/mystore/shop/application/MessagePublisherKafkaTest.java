package com.mystore.shop.application;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mystore.common.message.MessagePublisher;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MessagePublisherKafkaTest {

	@Autowired
	private MessagePublisher messagePublisher;

	@Test
	public void publishMessages() {
		messagePublisher.publishMessages();
	}

}
