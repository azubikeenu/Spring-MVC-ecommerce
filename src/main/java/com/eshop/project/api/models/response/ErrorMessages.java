package com.eshop.project.api.models.response;

public enum ErrorMessages {
	MISSING_REQUIRED_FIELD("Missing Required Field,Please check documentation for required fields"),
	RECORD_ALREADY_EXISTS("Record already exists in the database"), INTERNAL_SERVER_ERROR("Internal Server Error"),
	NO_RECORD_FOUND("No record with provided Id found"), AUTHENTICATION_FAILED("Authentication Failed"),
	COULD_NOT_UPDATE_RECORD("Could not update record"), COULD_NOT_DELETE_RECORD("Could not delete record"),
	COULD_NOT_CREATE_RECORD("Could not create record"), EMAIL_ADDRESS_NOT_VERIFIED("Email address not verified"),
	COULD_NOT_FIND_CATEGORY("Could not find category");

	private String errorMessages;

	ErrorMessages(String errorMessages) {
		this.errorMessages = errorMessages;
	}

	public String getErrorMessages() {
		return errorMessages;
	}

	public void setErrorMessages(String errorMessages) {
		this.errorMessages = errorMessages;
	}

}