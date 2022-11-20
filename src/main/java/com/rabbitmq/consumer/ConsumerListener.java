package com.rabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.rabbitmq.model.Customer;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ConsumerListener {

	@RabbitListener(queues = "${spring.rabbitmq.notification.queue}")
	public void listen(Customer message) {
		log.info("Receive message: [{}]", message);
	}
}
