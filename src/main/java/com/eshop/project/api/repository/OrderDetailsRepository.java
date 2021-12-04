package com.eshop.project.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eshop.project.api.entities.OrderDetails;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {

}
