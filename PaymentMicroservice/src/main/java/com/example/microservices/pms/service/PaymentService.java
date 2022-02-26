package com.example.microservices.pms.service;

import java.util.Random;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.microservices.pms.entiry.Payment;
import com.example.microservices.pms.repository.PaymentRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PaymentService {
	@Autowired
	private PaymentRepository paymentRepo;

	private Logger log = LoggerFactory.getLogger(PaymentService.class);

	public Payment doPayment(Payment payment) throws JsonProcessingException {
		payment.setPaymentStatus(paymentProcessing());
		payment.setTransactionId(UUID.randomUUID().toString());

		log.info("PaymentService request : {} ", new ObjectMapper().writeValueAsString(payment));

		return paymentRepo.save(payment);
	}

	private String paymentProcessing() {
		// api should be 3rd party payment gateway (paypal, paytm...)
		return new Random().nextBoolean() ? "success" : "fail";
	}

	public Payment getPaymentHistoryByOrderId(int orderId) throws JsonProcessingException {
		Payment payment = paymentRepo.findByOrderId(orderId);

		log.info("PaymentService getPaymentHistoryByOrderId : {} ", new ObjectMapper().writeValueAsString(payment));

		return payment;
	}
}
