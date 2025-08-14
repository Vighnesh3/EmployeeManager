package com.litmus7.employeemanager.exception;

public class EmployeeOperationException extends Exception {
    public EmployeeOperationException(String message) {
        super(message);
    }

    public EmployeeOperationException(String message, Throwable cause) {
        super(message, cause);
    }
}
