package com.litmus7.employeemanager.controller;

import static com.litmus7.employeemanager.constants.MessageConstants.*;


import com.litmus7.employeemanager.dto.Employee;
import com.litmus7.employeemanager.util.ValidationUtil;
import com.litmus7.employeemanager.exception.EmployeeServiceException;
import com.litmus7.employeemanager.service.EmployeeService;
import com.litmus7.employeemanager.util.ResponseUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class EmployeeController{
	private static final Logger logger = LogManager.getLogger(EmployeeController.class);
    private final EmployeeService employeeService = new EmployeeService();

    public ResponseUtil<Void> createEmployee(Employee employee) {
        try {if (!ValidationUtil.isValidId(employee.getId())) {
            return new ResponseUtil<>("ERROR", INVALID_ID, MSG_INVALID_ID, null);
        }
        if (!ValidationUtil.isValidName(employee.getFirstName())) {
            return new ResponseUtil<>("ERROR", INVALID_FIRST_NAME, MSG_INVALID_FIRST_NAME, null);
        }
        if (!ValidationUtil.isValidName(employee.getLastName())) {
            return new ResponseUtil<>("ERROR", INVALID_LAST_NAME, MSG_INVALID_LAST_NAME, null);
        }
        if (!ValidationUtil.isValidMobile(employee.getMobileNumber().trim())) {
            return new ResponseUtil<>("ERROR", INVALID_MOBILE, MSG_INVALID_MOBILE, null);
        }
        if (!ValidationUtil.isValidEmail(employee.getEmail())) {
            return new ResponseUtil<>("ERROR", INVALID_EMAIL, MSG_INVALID_EMAIL, null);
        }
        if (!ValidationUtil.isValidDate(employee.getJoiningDate())) {
            return new ResponseUtil<>("ERROR", INVALID_DATE, MSG_INVALID_DATE, null);
        }
            boolean created = employeeService.createEmployee(employee);
            if (created) {
            	return new ResponseUtil<>("SUCCESS", CREATED, MSG_CREATED, null);
            }
            return new ResponseUtil<>("FAIL", VALIDATION_FAILED,EMPLOYEE_CREATE_FAILED, null);
        } catch (EmployeeServiceException e) {
        	logger.error("Service exception while creating employee: {}", employee, e);
        	 return new ResponseUtil<>("FAIL", String.valueOf(e.getErrorCode()),e.getMessage(), null);
        } catch (Exception e) {
        	logger.error("Unexpected exception while creating employee: {}", employee, e);
        	return new ResponseUtil<>("FAIL", UNEXPECTED_ERROR,MSG_UNEXPECTED_ERROR, null);
        }
    }

    public ResponseUtil<List<Employee>> getAllEmployees() {
        try {
            List<Employee> employees = employeeService.getAllEmployees();
            if (employees.isEmpty()) {
            	
            	return new ResponseUtil<>("FAIL", EMPLOYEE_NOT_FOUND, MSG_EMPLOYEE_NOT_FOUND, employees);
            }
            return new ResponseUtil<>("SUCCESS", RETRIEVED, MSG_RETRIEVED, employees);
        } catch (EmployeeServiceException e) {
        	logger.error("Service exception while retrieving employees", e);
        	 return new ResponseUtil<>("ERROR", String.valueOf(e.getErrorCode()), e.getMessage(), null);
        }catch (Exception e) {
        	logger.error("Unexpected exception while retrieving employees", e);
        	return new ResponseUtil<>("ERROR", UNEXPECTED_ERROR, MSG_UNEXPECTED_ERROR, null);
        }
    }

    public ResponseUtil<Employee> getEmployeeById(int id) {
        try {
            Employee employee = employeeService.getEmployeeById(id);
            if (employee == null) {
            	
            	return new ResponseUtil<>("FAIL", EMPLOYEE_NOT_FOUND_WITH_GIVEN_ID, MSG_EMPLOYEE_NOT_FOUND_WITH_GIVEN_ID, null);
            }return new ResponseUtil<>("SUCCESS", RETRIEVED, MSG_RETRIEVED, employee);
            
        } catch (EmployeeServiceException e) {
        	logger.error("Service exception while retrieving employee with ID: {}", id, e);
        	 return new ResponseUtil<>("ERROR",String.valueOf( e.getErrorCode()), e.getMessage(), null);
        }catch (Exception e) {
        	logger.error("Unexpected exception while retrieving employee with ID: {}", id, e);
        	return new ResponseUtil<>("ERROR", UNEXPECTED_ERROR, MSG_UNEXPECTED_ERROR, null);
        }
    }

    public ResponseUtil<Void> deleteEmployee(int id) {
        try {
            if (employeeService.deleteEmployee(id) == 1) {
            	return new ResponseUtil<>("SUCCESS", DELETED, MSG_DELETED, null);
            }
            return new ResponseUtil<>("FAIL", EMPLOYEE_NOT_FOUND_WITH_GIVEN_ID, MSG_EMPLOYEE_NOT_FOUND_WITH_GIVEN_ID, null);
        } catch (EmployeeServiceException e) {
        	 logger.error("Service exception while deleting employee with ID: {}", id, e);
        	 return new ResponseUtil<>("ERROR",String.valueOf( e.getErrorCode()), e.getMessage(), null);
        }catch (Exception e) {
        	logger.error("Unexpected exception while deleting employee with ID: {}", id, e);
        	return new ResponseUtil<>("ERROR", UNEXPECTED_ERROR, MSG_UNEXPECTED_ERROR, null);
        }
    }

    public ResponseUtil<Void> updateEmployee(Employee employee) {
        try {
        	
            if (!ValidationUtil.isValidName(employee.getFirstName())) {
                return new ResponseUtil<>("ERROR", INVALID_FIRST_NAME, MSG_INVALID_FIRST_NAME, null);
            }
            if (!ValidationUtil.isValidName(employee.getLastName())) {
                return new ResponseUtil<>("ERROR", INVALID_LAST_NAME, MSG_INVALID_LAST_NAME, null);
            }
            if (!ValidationUtil.isValidMobile(employee.getMobileNumber().trim())) {
                return new ResponseUtil<>("ERROR", INVALID_MOBILE, MSG_INVALID_MOBILE, null);
            }
            if (!ValidationUtil.isValidEmail(employee.getEmail())) {
                return new ResponseUtil<>("ERROR", INVALID_EMAIL, MSG_INVALID_EMAIL, null);
            }
            if (!ValidationUtil.isValidDate(employee.getJoiningDate())) {
                return new ResponseUtil<>("ERROR", INVALID_DATE, MSG_INVALID_DATE, null);
            }
            if (employeeService.updateEmployee(employee) == 1) {
            	   return new ResponseUtil<>("SUCCESS", UPDATED, MSG_UPDATED, null);
            }
            return new ResponseUtil<>("FAIL", VALIDATION_FAILED, EMPLOYEE_UPDATE_FAILED, null);
        } catch (EmployeeServiceException e) {
        	logger.error("Service exception while updating employee: {}", employee, e);
        	return new ResponseUtil<>("ERROR", String.valueOf(e.getErrorCode()), e.getMessage(), null);
        }catch (Exception e) {
        	logger.error("Unexpected exception while updating employee: {}", employee, e);
        	 return new ResponseUtil<>("ERROR", UNEXPECTED_ERROR, MSG_UNEXPECTED_ERROR, null);
        }
    }
}
