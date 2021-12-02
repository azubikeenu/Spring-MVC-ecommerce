package com.eshop.project.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eshop.project.api.entities.CartItem;
import com.eshop.project.api.entities.Product;
import com.eshop.project.api.entities.User;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
	List<CartItem> findByUser(User user);

	List<CartItem> findByUserAndProduct(Product product, User user);

}
