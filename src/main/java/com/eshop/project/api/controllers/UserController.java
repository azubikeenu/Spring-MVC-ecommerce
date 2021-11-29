package com.eshop.project.api.controllers;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eshop.project.api.entities.User;
import com.eshop.project.api.models.request.UserRequestDetails;
import com.eshop.project.api.models.response.OperationStatusModel;
import com.eshop.project.api.models.response.RequestOperationName;
import com.eshop.project.api.models.response.RequestOperationStatus;
import com.eshop.project.api.models.response.UserResponse;
import com.eshop.project.api.services.UserService;

@RestController
@RequestMapping("/api/admin/users")
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<UserResponse> getUser(@PathVariable String id) {
		ModelMapper modelMapper = new ModelMapper();
		User returnedUser = userService.getUserById(id);
		UserResponse userResponse = modelMapper.map(returnedUser, UserResponse.class);

		return ResponseEntity.status(HttpStatus.OK).body(userResponse);
	}

	@PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<UserResponse> saveUser(@RequestBody UserRequestDetails userRequest) {

		User createdUser = userService.createUser(userRequest);
		ModelMapper modelMapper = new ModelMapper();
		UserResponse userResponse = modelMapper.map(createdUser, UserResponse.class);
		return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
	}

	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<UserResponse>> getUsers() {
		List<UserResponse> returnedValue = new ArrayList<>();
		List<User> users = userService.getUsers();
		if (users != null && !users.isEmpty()) {
			Type listType = new TypeToken<List<UserResponse>>() {
			}.getType();
			returnedValue = new ModelMapper().map(users, listType);
		}

		return ResponseEntity.status(HttpStatus.OK).body(returnedValue);
	}

	@PutMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<UserResponse> updateUser(@PathVariable String id,
			@RequestBody UserRequestDetails userRequestDetails) {
		User updatedUser = userService.updateUser(id, userRequestDetails);
		UserResponse userResponse = new ModelMapper().map(updatedUser, UserResponse.class);
		return ResponseEntity.status(HttpStatus.OK).body(userResponse);

	}

	@DeleteMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public OperationStatusModel deleteUser(@PathVariable String id) {
		OperationStatusModel returnedValue = new OperationStatusModel();
		userService.deleteUser(id);
		returnedValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
		returnedValue.setOperationName(RequestOperationName.DELETE.name());
		return returnedValue;

	}

	@PostMapping("/check_email")
	public OperationStatusModel checkDuplicateEmail(@Param("email") String email) {
		User uniqueEmail = userService.isUniqueEmail(email);
		if (uniqueEmail == null) {
			OperationStatusModel returnedValue = new OperationStatusModel();
			returnedValue.setOperationResult(RequestOperationStatus.EMAIL_DOESNT_EXIST.name());
			returnedValue.setOperationName(RequestOperationName.IS_EMAIL_UNIQUE.name());
			return returnedValue;
		}
		return null;
	}

}
