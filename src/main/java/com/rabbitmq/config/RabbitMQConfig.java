package com.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

	@Value("${spring.rabbitmq.hostname}")
	private String hostname;

	@Value("${spring.rabbitmq.username}")
	private String username;

	@Value("${spring.rabbitmq.password}")
	private String password;
	
	@Value("${spring.rabbitmq.notification.exchange}")
	private String exchange;

	@Value("${spring.rabbitmq.notification.queue}")
	private String queue;

	@Value("${spring.rabbitmq.notification.routekey}")
	private String routingKey;

	@Bean
	public Queue queue() {
		return new Queue(queue, true);
	}

	@Bean
	public TopicExchange exchange() {
		return  new TopicExchange(exchange);
	}

	@Bean
	public Binding binding() {
		return BindingBuilder.bind(queue()).to(exchange()).with(routingKey);
	}

	@Bean
	public CachingConnectionFactory connectionFactory() {
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory(hostname);
		connectionFactory.setUsername(username);
		connectionFactory.setPassword(password);
		return connectionFactory;
	}
	
	@Bean
	public SimpleMessageListenerContainer container() {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory());
		return container;
	}

	@Bean
	public RabbitTemplate rabbitTemplate() {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
		rabbitTemplate.setMessageConverter(converter());
		return rabbitTemplate;
	}

	@Bean
	public Jackson2JsonMessageConverter converter() {
		return new Jackson2JsonMessageConverter();
	}
}
