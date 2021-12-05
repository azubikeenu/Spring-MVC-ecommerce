package com.eshop.project.api.services;

import java.util.List;
import java.util.Set;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.eshop.project.api.entities.Order;
import com.eshop.project.api.entities.User;
import com.eshop.project.api.models.request.UserRequestDetails;

public interface UserService extends UserDetailsService {
	public User createUser(UserRequestDetails user);

	public User getUserById(String userId);

	public User getUserByEmail(String email);

	public User updateUser(String userId, UserRequestDetails userRequestDetails);

	public void deleteUser(String userId);

	public List<User> getUsers();

	public User isUniqueEmail(String email);

	public User updateProfile(User user);

	public Set<Order> getAllOrders(String userId);

	public User saveUser(User user);
}
