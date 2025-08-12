package com.litmus7.employeemanager.service;

import com.litmus7.employeemanager.dao.EmployeeDAO;
import com.litmus7.employeemanager.dto.Employee;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class EmployeeService {
    private final EmployeeDAO dao = new EmployeeDAO();
    Scanner sc=new Scanner(System.in);
    
    public boolean createEmployee(Employee employee) throws SQLException {
    	try {
			return dao.createEmployee(employee)>0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
    	
    }

    public List<Employee> getAllEmployees() throws SQLException{
        try {
			return dao.getAllEmployees();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
         
        
    }
    public Employee getEmployeeById(int id) {
        Employee employee = null;
		try {
			return dao.getEmployeeById(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return employee;
        
    }

    public int deleteEmployee(int id) throws SQLException {
        try {
			return dao.deleteEmployee(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
        
    }

    public int updateEmployee(Employee employee) throws SQLException {
    	
        try {
			return dao.updateEmployee(employee);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
       
    }

    
   
}
