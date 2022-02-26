package com.example.microservices.oms.exception;

public class OrderException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OrderException(String message) {
		super(message);
	}
}
