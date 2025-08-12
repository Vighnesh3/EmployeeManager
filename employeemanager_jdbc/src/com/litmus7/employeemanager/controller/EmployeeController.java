package com.litmus7.employeemanager.controller;

import static com.litmus7.employeemanager.constants.MessageConstants.*;

import com.litmus7.employeemanager.dto.Employee;
import com.litmus7.employeemanager.service.EmployeeService;
import com.litmus7.employeemanager.util.ResponseUtil;

import java.sql.SQLException;
import java.util.List;

public class EmployeeController {

	private final EmployeeService service = new EmployeeService();

    public ResponseUtil<String> createEmployee(Employee employee) throws SQLException {
       
            try{boolean created = service.createEmployee(employee);
            if (created) {
                return new ResponseUtil<>("SUCCESS", EMPLOYEE_CREATED_SUCCESS, null);
            }
            return new ResponseUtil<>("FAIL", EMPLOYEE_CREATION_FAILED, null);}
            catch(Exception e) {
            
        }
			return null; }
    

    public ResponseUtil<List<Employee>> getAllEmployees() throws SQLException {
    	List<Employee> employees = service.getAllEmployees();
           try { 
            if (employees.isEmpty()) {
                return new ResponseUtil<>("FAIL", EMPLOYEE_NOT_FOUND, employees);
            }
            return new ResponseUtil<>("SUCCESS", EMPLOYEE_RETRIEVED_SUCCESS, employees);}
           catch(Exception e) {
             e.printStackTrace();           }
        return null;
      
    }
    public ResponseUtil<Employee> getEmployeeById(int id)  {
        Employee employee = service.getEmployeeById(id);
		try{if (employee == null) {
		    return new ResponseUtil<>("FAIL", EMPLOYEE_NOT_FOUND_WITH_GIVEN_ID, null);
		}
		return new ResponseUtil<>("SUCCESS", EMPLOYEE_RETRIEVED_SUCCESS, employee);
		
    }catch(Exception e) {
    	e.printStackTrace();
    }
		return null;
    }

    public ResponseUtil<String> deleteEmployee(int id) throws SQLException  {
       
            try{if (service.deleteEmployee(id)==1) {
                return new ResponseUtil<>("SUCCESS", EMPLOYEE_DELETED_SUCCESS, null);
            }
            return new ResponseUtil<>("FAIL", EMPLOYEE_NOT_FOUND_WITH_GIVEN_ID, null);
            }catch(Exception e) {
            	e.printStackTrace();
            }
            return null;
    }

    public ResponseUtil<String> updateEmployee(Employee employee) throws SQLException {
       try { if (service.updateEmployee(employee)==1) {
		    return new ResponseUtil<>("SUCCESS", EMPLOYEE_UPDATED_SUCCESS, null);
		}
		return new ResponseUtil<>("FAIL", EMPLOYEE_UPDATE_FAILED, null);
    }catch(Exception e) {
    e.printStackTrace();	
    }return null;
    }

    
}
