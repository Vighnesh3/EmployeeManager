package com.litmus7.employeemanager.service;

import com.litmus7.employeemanager.dao.EmployeeDAO;
import com.litmus7.employeemanager.dto.Employee;
import com.litmus7.employeemanager.exception.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class EmployeeService {
    private final EmployeeDAO dao = new EmployeeDAO();
    Scanner sc=new Scanner(System.in);
    
    public boolean createEmployee(Employee employee) throws EmployeeOperationException {
    	try {
			return dao.createEmployee(employee)>0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new EmployeeOperationException("Failed to create employee",e.getCause());
		}
		
    	
    }

    public List<Employee> getAllEmployees() throws  EmployeeOperationException{
        try {
			return dao.getAllEmployees();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new EmployeeOperationException("No employees in the database",e.getCause());
		}
		
         
        
    }
    public Employee getEmployeeById(int id) throws EmployeeOperationException {
        
		try {
			Employee employee = dao.getEmployeeById(id);;
			return employee;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new EmployeeOperationException("No employee with given id",e.getCause());
		}
        //return employee;
        
    }

    public int deleteEmployee(int id) throws EmployeeOperationException {
        try {
			return dao.deleteEmployee(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new EmployeeOperationException("No employee with given id",e.getCause());
		}
	
        
    }

    public int updateEmployee(Employee employee) throws  EmployeeOperationException {
    	
        try {
			return dao.updateEmployee(employee);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new EmployeeOperationException("No employee with given id",e.getCause());
		}
		
       
    }

    
   
}
