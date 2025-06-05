package com.app.Springboot.Exception;

public class EmailAlreadyExistsException extends RuntimeException {
	
	 int errorCode;

	public EmailAlreadyExistsException(String message, int errorCode) {
		super(message);
		this.errorCode= errorCode;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}


	

}
