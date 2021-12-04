package com.eshop.project.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.eshop.project.api.entities.CartItem;
import com.eshop.project.api.entities.Product;
import com.eshop.project.api.entities.User;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
	List<CartItem> findByUser(User user);

	List<CartItem> findByUserAndProduct(Product product, User user);

	@Transactional
	@Modifying
	@Query("delete  CartItem c WHERE c.user  =:user")
	public void deleteByCustomer(@Param("user") User user);

}
