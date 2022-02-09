package com.example.microservices.oms.model;

import com.example.microservices.oms.entity.Order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequest {
	private Order order;
	private Payment payment;
}
