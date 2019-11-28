package com.example.demo.exceptions;

public class ErrorMessage {

	private String error;
	private String description;
	
	public ErrorMessage (Exception exception) {
		this(exception.getClass().getName(), exception.getMessage());
	}

	public ErrorMessage(String error, String description) {
		super();
		this.error = error;
		this.description = description;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "ApiErrorMessage [error=" + error + ", description=" + description + "]";
	}
	
}
