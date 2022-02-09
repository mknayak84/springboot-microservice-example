package com.example.microservices.pms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.microservices.pms.entiry.Payment;
import com.example.microservices.pms.service.PaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentController {
	@Autowired
	private PaymentService service;
	
	@PostMapping("/doPayment")
	public Payment doPayment(@RequestBody Payment newPayment) {
		return service.doPayment(newPayment);
	}
	
	@GetMapping("/{orderId}")
	public Payment getPaymentHistoryByOrderId(@PathVariable int orderId) {
		return service.getPaymentHistoryByOrderId(orderId);
	}
}
