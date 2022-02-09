package com.example.microservices.oms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.microservices.oms.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
