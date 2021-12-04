package com.eshop.project.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eshop.project.api.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
	Order findByOrderId(String orderId);
}
