package com.eshop.project.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eshop.project.api.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUserId(String userId);

	User findByEmail(String email);

}
