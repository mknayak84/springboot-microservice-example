package com.example.microservices.pms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.microservices.pms.entiry.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

	Payment findByOrderId(int id);
}
