package com.example.microservices.pms.service;

import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.microservices.pms.entiry.Payment;
import com.example.microservices.pms.repository.PaymentRepository;

@Service
public class PaymentService {
	@Autowired
	private PaymentRepository paymentRepo;
	
	public Payment doPayment(Payment payment) {
		payment.setPaymentStatus(paymentProcessing());
		payment.setTransactionId(UUID.randomUUID().toString());
		return paymentRepo.save(payment);
	}
	private String paymentProcessing() {
		// api should be 3rd party payment gateway (paypal, paytm...)
		return new Random().nextBoolean() ? "success" : "fail";
	}
	public Payment getPaymentHistoryByOrderId(int orderId) {
		return paymentRepo.findByOrderId(orderId);
	}
}
