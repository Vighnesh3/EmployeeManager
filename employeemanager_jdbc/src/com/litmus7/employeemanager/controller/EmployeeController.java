package com.litmus7.employeemanager.controller;

import static com.litmus7.employeemanager.constants.MessageConstants.*;

import com.litmus7.employeemanager.dto.Employee;

import com.litmus7.employeemanager.exception.EmployeeOperationException;
import com.litmus7.employeemanager.service.EmployeeService;
import com.litmus7.employeemanager.util.ResponseUtil;

import java.sql.SQLException;
import java.util.List;

public class EmployeeController{

    private final EmployeeService service = new EmployeeService();

    public ResponseUtil<String> createEmployee(Employee employee) throws EmployeeOperationException{
        try {
            boolean created = service.createEmployee(employee);
            if (created) {
                return new ResponseUtil<>("SUCCESS", EMPLOYEE_CREATED_SUCCESS, null);
            }
            return new ResponseUtil<>("FAIL", EMPLOYEE_CREATION_FAILED, null);
        } catch (EmployeeOperationException e) {
            throw new EmployeeOperationException("Database error while creating employee", e);
        } catch (Exception e) {
            throw new EmployeeOperationException("Unexpected error while creating employee", e);
        }
    }

    public ResponseUtil<List<Employee>> getAllEmployees() throws EmployeeOperationException{
        try {
            List<Employee> employees = service.getAllEmployees();
            if (employees.isEmpty()) {
            	
            	return new ResponseUtil<>("FAIL", EMPLOYEE_NOT_FOUND, employees);
            }
            return new ResponseUtil<>("SUCCESS", EMPLOYEE_RETRIEVED_SUCCESS, employees);
        } catch (Exception e) {
            throw new EmployeeOperationException("Database error while retrieving employees", e);
        }
    }

    public ResponseUtil<Employee> getEmployeeById(int id) throws EmployeeOperationException{
        try {
            Employee employee = service.getEmployeeById(id);
            if (employee == null) {
            	
            	return new ResponseUtil<>("FAIL", EMPLOYEE_NOT_FOUND_WITH_GIVEN_ID, employee);
            }
            return new ResponseUtil<>("SUCCESS", EMPLOYEE_RETRIEVED_SUCCESS, employee);
        } catch (Exception e) {
            throw new EmployeeOperationException("Error while fetching employee by ID: " + id, e);
        }
    }

    public ResponseUtil<String> deleteEmployee(int id) throws EmployeeOperationException{
        try {
            if (service.deleteEmployee(id) == 1) {
                return new ResponseUtil<>("SUCCESS", EMPLOYEE_DELETED_SUCCESS, null);
            }
            return new ResponseUtil<>("FAIL", EMPLOYEE_NOT_FOUND_WITH_GIVEN_ID, null);
        } catch (Exception e) {
            throw new EmployeeOperationException("Database error while deleting employee", e);
        }
    }

    public ResponseUtil<String> updateEmployee(Employee employee) throws EmployeeOperationException{
        try {
            if (service.updateEmployee(employee) == 1) {
                return new ResponseUtil<>("SUCCESS", EMPLOYEE_UPDATED_SUCCESS, null);
            }
            return new ResponseUtil<>("FAIL", EMPLOYEE_UPDATE_FAILED, null);
        } catch (Exception e) {
            throw new EmployeeOperationException("Database error while updating employee", e);
        }
    }
}
