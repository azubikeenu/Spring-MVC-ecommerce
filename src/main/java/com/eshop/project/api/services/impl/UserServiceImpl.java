package com.eshop.project.api.services.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.eshop.project.api.entities.Role;
import com.eshop.project.api.entities.User;
import com.eshop.project.api.exceptions.UserServiceException;
import com.eshop.project.api.models.request.UserRequestDetails;
import com.eshop.project.api.models.response.ErrorMessages;
import com.eshop.project.api.repository.RoleRepository;
import com.eshop.project.api.repository.UserRepository;
import com.eshop.project.api.services.UserService;
import com.eshop.project.api.shared.utils.Utils;
import com.eshop.project.security.UserSecurity;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private Utils utils;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User userEntity = userRepository.findByEmail(email);

		if (userEntity == null) {
			throw new UsernameNotFoundException("user not found");
		}

		return new UserSecurity(userEntity);
	}

	@Override
	public User createUser(UserRequestDetails user) {
		User userFound = userRepository.findByEmail(user.getEmail());
		if (userFound != null)
			throw new UserServiceException(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessages());

		ModelMapper modelMapper = new ModelMapper();

		Set<Role> roles = user.getRoles().stream().map(role -> roleRepository.findByName(role))
				.collect(Collectors.toSet());

		User userEntity = modelMapper.map(user, User.class);
		userEntity.setRoles(roles);
		userEntity.setUserId(utils.generateRandomUserId(30));
		userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
		User returnedUser = userRepository.save(userEntity);
		return returnedUser;
	}

	@Override
	public User getUserById(String userId) {
		User returnedUser = userRepository.findByUserId(userId);
		if (returnedUser == null)
			throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessages());
		return returnedUser;
	}

	@Override
	public User getUserByEmail(String email) {
		User foundUser = userRepository.findByEmail(email);
		if (foundUser == null)
			throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessages());
		return foundUser;
	}

	@Override
	public User updateUser(String userId, UserRequestDetails userRequestDetails) {
		User userFound = userRepository.findByUserId(userId);
		if (userFound == null)
			throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessages());
		userFound.setFirstName(utils.normalizeString(userRequestDetails.getFirstName()));
		userFound.setLastName(utils.normalizeString(userRequestDetails.getLastName()));
		userFound.setActive(userRequestDetails.isActive());
		Set<Role> roles = new HashSet<>();
		if (userRequestDetails.getRoles().size() > 0) {
			roles = userRequestDetails.getRoles().stream().map(roleRepository::findByName).collect(Collectors.toSet());
			userFound.setRoles(roles);
		}
		return userRepository.save(userFound);

	}

	@Override
	public void deleteUser(String userId) {
		User foundUser = userRepository.findByUserId(userId);
		if (foundUser == null)
			throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessages());
		userRepository.delete(foundUser);
	}

	@Override
	public List<User> getUsers() {
		List<User> users = userRepository.findAll();
		return users;
	}

	@Override
	public User isUniqueEmail(String email) {
		User userFound = userRepository.findByEmail(email);
		return userFound;
	}

	@Override
	public User updateProfile(User user) {
		User persistedUser = userRepository.findByEmail(user.getEmail());
		persistedUser.setFirstName(user.getFirstName());
		persistedUser.setLastName(user.getLastName());
		persistedUser.setActive(user.isActive());

		if (!user.getPassword().isEmpty()) {
			persistedUser.setPassword(user.getPassword());
			persistedUser.setPassword(passwordEncoder.encode(persistedUser.getPassword()));

		}

		User updatedUser = userRepository.save(persistedUser);
		return updatedUser;
	}

}
