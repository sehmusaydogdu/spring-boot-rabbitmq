package com.rabbitmq.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rabbitmq.model.Customer;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ProducerController {

	@Value("${spring.rabbitmq.notification.exchange}")
	private String exchange;

	@Value("${spring.rabbitmq.notification.routekey}")
	private String routingKey;

	private final RabbitTemplate rabbitTemplate;

	public ProducerController(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	@PostMapping("/sendMessage")
	public void sendMessage(@RequestBody Customer customer) {
		rabbitTemplate.convertAndSend(exchange, routingKey, customer);
		log.info("Send sent: [{}]", customer);
	}

	@GetMapping("/send")
	public void send() {
		Customer customer = new Customer();
		customer.setCustomerId(System.currentTimeMillis());
		customer.setMessage("Hello world");
		rabbitTemplate.convertAndSend(exchange, routingKey, customer);
		log.info("Send sent: [{}]", customer);
	}
}
