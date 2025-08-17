package com.litmus7.employeemanager.exception;

public class EmployeeServiceException extends Exception {
	 private final String errorCode;  

	    public EmployeeServiceException(String errorCode, String message, Throwable cause) {
	        super(message, cause);
	        this.errorCode = errorCode;
	    }

	    public EmployeeServiceException(String errorCode, String message) {
	        super(message);
	        this.errorCode = errorCode;
	    }

	public Object getErrorCode() {
		// TODO Auto-generated method stub
		return errorCode;
	}
}