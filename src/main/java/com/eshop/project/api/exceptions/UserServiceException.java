package com.eshop.project.api.exceptions;

public class UserServiceException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public UserServiceException() {
		super();
	}

	public UserServiceException(String message) {
		super(message);

	}

}