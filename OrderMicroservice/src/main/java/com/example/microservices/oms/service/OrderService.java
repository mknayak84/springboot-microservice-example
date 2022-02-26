package com.example.microservices.oms.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.microservices.oms.entity.Order;
import com.example.microservices.oms.model.Payment;
import com.example.microservices.oms.model.TransactionRequest;
import com.example.microservices.oms.model.TransactionResponse;
import com.example.microservices.oms.repository.OrderRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@RefreshScope
public class OrderService {
	@Autowired
	private OrderRepository orderRepo;

	@Autowired
	@Lazy
	private RestTemplate template;

	@Value("${microservice.payment-service.endpoints.endpoint.uri}")
	private String ENDPOINT_URL;

	private Logger log = LoggerFactory.getLogger(OrderService.class);

	public TransactionResponse saveOrder(TransactionRequest request) throws JsonProcessingException {
		String response = "";
		Order order = request.getOrder();
		Payment payment = request.getPayment();
		payment.setOrderId(order.getId());
		payment.setAmount(order.getPrice());

		log.info("OrderService request : {} ", new ObjectMapper().writeValueAsString(request));

		// rest call
		// Payment paymentResponse =
		// template.postForObject("http://PAYMENT-SERVICE/payment/doPayment", payment,
		// Payment.class);

		Payment paymentResponse = template.postForObject(ENDPOINT_URL, payment, Payment.class);

		log.info("PaymentService response from OrderService rest call : {} ",
				new ObjectMapper().writeValueAsString(paymentResponse));

		response = paymentResponse.getPaymentStatus().equals("success")
				? "payment processing successful and order placed"
				: "there is a failure in payment api, order added to cart";

		orderRepo.save(order);
		return new TransactionResponse(order, paymentResponse.getAmount(), paymentResponse.getTransactionId(),
				response);
	}
}
