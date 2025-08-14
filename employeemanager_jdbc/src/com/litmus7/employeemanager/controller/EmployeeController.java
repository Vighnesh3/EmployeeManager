package com.litmus7.employeemanager.controller;

import static com.litmus7.employeemanager.constants.MessageConstants.*;


import com.litmus7.employeemanager.dto.Employee;

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
        try {
            boolean created = employeeService.createEmployee(employee);
            if (created) {
                return new ResponseUtil<>("SUCCESS", EMPLOYEE_CREATION_SUCCESS, null);
            }
            return new ResponseUtil<>("FAIL", EMPLOYEE_CREATION_FAILED, null);
        } catch (EmployeeServiceException e) {
        	logger.error("Service exception while creating employee: {}", employee, e);
        	 return new ResponseUtil<>("FAIL", e.getMessage(), null);
        } catch (Exception e) {
        	logger.error("Unexpected exception while creating employee: {}", employee, e);
        	return new ResponseUtil<>("FAIL", e.getMessage(), null);
        }
    }

    public ResponseUtil<List<Employee>> getAllEmployees() {
        try {
            List<Employee> employees = employeeService.getAllEmployees();
            if (employees.isEmpty()) {
            	
            	return new ResponseUtil<>("FAIL", EMPLOYEE_NOT_FOUND, employees);
            }
            return new ResponseUtil<>("SUCCESS", EMPLOYEE_RETRIEVED_SUCCESS, employees);
        } catch (EmployeeServiceException e) {
        	logger.error("Service exception while retrieving employees", e);
        	return new ResponseUtil<>("FAIL", e.getMessage(), null);
        }catch (Exception e) {
        	logger.error("Unexpected exception while retrieving employees", e);
        	return new ResponseUtil<>("FAIL", e.getMessage(), null);
        }
    }

    public ResponseUtil<Employee> getEmployeeById(int id) {
        try {
            Employee employee = employeeService.getEmployeeById(id);
            if (employee == null) {
            	
            	return new ResponseUtil<>("FAIL", EMPLOYEE_NOT_FOUND_WITH_GIVEN_ID, employee);
            }
            return new ResponseUtil<>("SUCCESS", EMPLOYEE_RETRIEVED_SUCCESS, employee);
        } catch (EmployeeServiceException e) {
        	logger.error("Service exception while retrieving employee with ID: {}", id, e);
        	return new ResponseUtil<>("FAIL", e.getMessage(), null);
        }catch (Exception e) {
        	logger.error("Unexpected exception while retrieving employee with ID: {}", id, e);
        	return new ResponseUtil<>("FAIL", e.getMessage(), null);
        }
    }

    public ResponseUtil<Void> deleteEmployee(int id) {
        try {
            if (employeeService.deleteEmployee(id) == 1) {
                return new ResponseUtil<>("SUCCESS", EMPLOYEE_DELETED_SUCCESS, null);
            }
            return new ResponseUtil<>("FAIL", EMPLOYEE_NOT_FOUND_WITH_GIVEN_ID, null);
        } catch (EmployeeServiceException e) {
        	 logger.error("Service exception while deleting employee with ID: {}", id, e);
        	return new ResponseUtil<>("FAIL", e.getMessage(), null);
        }catch (Exception e) {
        	logger.error("Unexpected exception while deleting employee with ID: {}", id, e);
        	return new ResponseUtil<>("FAIL", e.getMessage(), null);
        }
    }

    public ResponseUtil<Void> updateEmployee(Employee employee) {
        try {
            if (employeeService.updateEmployee(employee) == 1) {
                return new ResponseUtil<>("SUCCESS", EMPLOYEE_UPDATED_SUCCESS, null);
            }
            return new ResponseUtil<>("FAIL", EMPLOYEE_UPDATE_FAILED, null);
        } catch (EmployeeServiceException e) {
        	logger.error("Service exception while updating employee: {}", employee, e);
        	return new ResponseUtil<>("FAIL", e.getMessage(), null);
        }catch (Exception e) {
        	logger.error("Unexpected exception while updating employee: {}", employee, e);
        	return new ResponseUtil<>("FAIL", e.getMessage(), null);
        }
    }
}
