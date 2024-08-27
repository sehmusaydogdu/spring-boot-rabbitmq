package com.rabbitmq.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Customer {
	private long customerId;
	private String message;
}
