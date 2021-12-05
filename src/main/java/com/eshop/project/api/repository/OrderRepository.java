package com.eshop.project.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eshop.project.api.entities.Order;
import com.eshop.project.api.entities.User;

public interface OrderRepository extends JpaRepository<Order, Long> {
	Order findByOrderId(String orderId);

	Order findByUser(User user);

}
