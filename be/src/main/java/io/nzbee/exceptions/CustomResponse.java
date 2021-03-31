package io.nzbee.exceptions;

public class CustomResponse {

	private String exception;

	public CustomResponse(String response) {
		this.setException(response);
	}

	public String getException() {
		return exception;
	}

	public void setException(String message) {
		this.exception = message;
	}
	
}
