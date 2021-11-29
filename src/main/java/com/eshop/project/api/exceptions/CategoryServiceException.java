package com.eshop.project.api.exceptions;

public class CategoryServiceException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public CategoryServiceException() {
		super();
	}

	public CategoryServiceException(String message) {
		super(message);
	}

}
