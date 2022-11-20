package com.rabbitmq.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private long customerId;
	private String message;
}
