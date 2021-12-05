package com.eshop.project.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eshop.project.api.entities.User;
import com.eshop.project.api.models.response.OperationStatusModel;
import com.eshop.project.api.models.response.RequestOperationName;
import com.eshop.project.api.models.response.RequestOperationStatus;
import com.eshop.project.api.services.UserService;

@RestController
public class EmailController {
	@Autowired
	private UserService userService;

	@PostMapping("/api/check_email")
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
