package com.eshop.project.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eshop.project.api.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByName(String name);
}
