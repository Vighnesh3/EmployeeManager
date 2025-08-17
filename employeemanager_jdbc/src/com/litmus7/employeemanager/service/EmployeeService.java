package com.litmus7.employeemanager.service;

import com.litmus7.employeemanager.constants.ErrorCodes;
import com.litmus7.employeemanager.dao.EmployeeDAO;
import com.litmus7.employeemanager.dto.Employee;
import com.litmus7.employeemanager.exception.*;
import java.util.List;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class EmployeeService {
	private static final Logger logger=LogManager.getLogger("EmployeeService.class");
    private final EmployeeDAO employeeDao = new EmployeeDAO();
    Scanner sc=new Scanner(System.in);
    
    public boolean createEmployee(Employee employee) throws EmployeeServiceException {
    	try {
    		
			boolean result=employeeDao.createEmployee(employee)>0;
			if (result) {
                logger.info("Employee created successfully with ID: {}", employee.getId());
            } else {
                logger.warn("Failed to create employee ");
            }
			return result;
		} catch (EmployeeDaoException e) {
			logger.error("Service layer failed to create employee: {}", employee, e);
			// TODO Auto-generated catch block
			throw new EmployeeServiceException( ErrorCodes.EMPLOYEE_CREATION_FAILED,"Service layer Failed to create employee",e);
		}finally {
            logger.trace("Exiting createEmployee()");
        }
		
    	
    }

    public List<Employee> getAllEmployees() throws  EmployeeServiceException{
        try {
        	List<Employee> employees=employeeDao.getAllEmployees();
        	 logger.info("Fetched {} employees", employees.size());
			return employees;
		} catch (EmployeeDaoException e) {
			logger.error("Service layer unable to fetch employee data", e);
			// TODO Auto-generated catch block
			throw new EmployeeServiceException(ErrorCodes.EMPLOYEE_FETCH_FAILED,"Service layer unable to fetch employee data",e);
		}
        finally {
            logger.trace("Exiting getAllEmployees()");
        }
         
        
    }
    public Employee getEmployeeById(int id) throws EmployeeServiceException {
        
		try {
			Employee employee = employeeDao.getEmployeeById(id);
			if (employee != null) {
                logger.info("Employee data fetched successfully with ID: {}", id);
            } else {
                logger.warn("No employee found with ID: {}", id);
            }
			return employee;
		} catch (EmployeeDaoException e) {
			logger.error("Service layer unable to fetch employee data with ID: {}", id, e);
			// TODO Auto-generated catch block
			throw new EmployeeServiceException(ErrorCodes.EMPLOYEE_RETRIEVE_FAILED,"Service layer unable to fetch employee data",e);
		}finally {
            logger.trace("Exiting getEmployeeById()");
        }
        //return employee;
        
    }

    public int deleteEmployee(int id) throws EmployeeServiceException {
        try {
        	int rows=employeeDao.deleteEmployee(id);
        	if (rows > 0) {
                logger.info("Employee deleted successfully: {}", id);
            } else {
                logger.warn("No employee deleted. ID not found: {}", id);
            }
			return rows;
		} catch (EmployeeDaoException e) {
			// TODO Auto-generated catch block
			logger.error("Service layer unable to delete employee data for ID: {}", id, e);
			throw new EmployeeServiceException(ErrorCodes.EMPLOYEE_DELETE_FAILED,"Service layer unable to delete employee data",e);
		}finally {
            logger.trace("Exiting deleteEmployee()");
        }
	
        
    }

    public int updateEmployee(Employee employee) throws  EmployeeServiceException {
    	
        try {
        	int rows=employeeDao.updateEmployee(employee);
        			if (rows > 0) {
            logger.info("Updated employee with ID: {}", employee.getId());
        } else {
            logger.warn("No employee updated. ID not found: {}", employee.getId());
        }
			return rows;
		} catch (EmployeeDaoException e) {
			// TODO Auto-generated catch block
			logger.error("Service layer unable to update employee data: {}", employee, e);
			throw new EmployeeServiceException( ErrorCodes.EMPLOYEE_UPDATE_FAILED,"Service layer unable to update employee data",e.getCause());
		}finally {
            logger.trace("Exiting updateEmployee()");
        }
		
       
    }

    
   
}
