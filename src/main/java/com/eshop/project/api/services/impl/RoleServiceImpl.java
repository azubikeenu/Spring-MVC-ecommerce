package com.eshop.project.api.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eshop.project.api.entities.Role;
import com.eshop.project.api.repository.RoleRepository;
import com.eshop.project.api.services.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public List<Role> findAllRoles() {
		return roleRepository.findAll();
	}

}
