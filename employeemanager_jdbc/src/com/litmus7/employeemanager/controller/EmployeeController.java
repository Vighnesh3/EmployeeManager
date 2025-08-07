package com.litmus7.employeemanager.controller;

import com.litmus7.employeemanager.dto.Employee;
import com.litmus7.employeemanager.service.EmployeeService;
import com.litmus7.employeemanager.util.ResponseUtil;
import java.util.List;

public class EmployeeController {

    private final EmployeeService employeeService = new EmployeeService();

    public ResponseUtil<String> createEmployee() {
        return employeeService.createEmployee();
    }

    public ResponseUtil<List<Employee>> getAllEmployees() {
        return employeeService.getAllEmployees();
    }
    public ResponseUtil<Employee> getEmployeeById(int empId) {
        return employeeService.getEmployeeById(empId);
    }

    public ResponseUtil<String> updateEmployee() {
        return employeeService.updateEmployee();
    }

    public ResponseUtil<String> deleteEmployee(int empId) {
        return employeeService.deleteEmployee(empId);
    }

   
}
