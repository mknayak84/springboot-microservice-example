package com.example.microservices.oms.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.microservices.oms.exception.OrderException;

@RestControllerAdvice
public class OrderExceptionController {

	@ExceptionHandler(OrderException.class)
	public ResponseEntity<String> getOrderException(OrderException orderException) {
		return new ResponseEntity<String>(orderException.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<String> getOrderException(RuntimeException runtimeException) {
		return new ResponseEntity<String>(runtimeException.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
