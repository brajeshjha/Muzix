package com.stackroute.accountmanager.exception;

@SuppressWarnings("serial")
public class UserAlreadyExistsException extends Exception{
	
	private String message;
	
	public UserAlreadyExistsException() {
		
	}

	public UserAlreadyExistsException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "UserAlreadyExistsException [message=" + message + "]";
	}

}


