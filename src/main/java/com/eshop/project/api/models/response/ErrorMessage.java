package com.eshop.project.api.models.response;

import java.util.Date;

public class ErrorMessage {
	private Date timeStamp;
	private String message;
	private int status;

	public ErrorMessage() {
	}

	public ErrorMessage(Date timeStamp, String message, int status) {
		this.timeStamp = timeStamp;
		this.message = message;
		this.status = status;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}