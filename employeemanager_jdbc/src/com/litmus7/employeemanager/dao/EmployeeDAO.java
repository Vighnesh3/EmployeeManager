package com.litmus7.employeemanager.dao;
import com.litmus7.employeemanager.dto.Employee;
import com.litmus7.employeemanager.util.ConnectionUtil;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.litmus7.employeemanager.exception.*;
public class EmployeeDAO {
	private static final Logger logger = LogManager.getLogger(EmployeeDAO.class);
	private static final String INSERT_EMPLOYEE =
            "INSERT INTO employee (emp_id, first_name, last_name, mobile_number, email, joining_date, active_status) VALUES (?, ?, ?, ?, ?, ?, ?)";

    private static final String GET_ALL_EMPLOYEES =
            "SELECT emp_id, first_name, last_name, mobile_number, email, joining_date, active_status FROM employee";

    private static final String GET_EMPLOYEE_BY_ID =
            "SELECT emp_id, first_name, last_name, mobile_number, email, joining_date, active_status FROM employee WHERE emp_id=?";

    private static final String DELETE_EMPLOYEE =
            "DELETE FROM employee WHERE emp_id=?";

    private static final String UPDATE_EMPLOYEE =
            "UPDATE employee SET first_name=?, last_name=?, mobile_number=?, email=?, joining_date=?, active_status=? WHERE emp_id=?";
    public int createEmployee(Employee employee) throws EmployeeDaoException {
        String queryToInsertEmployee = INSERT_EMPLOYEE;
        logger.info("Attempting to create employee: {}", employee);
        try (Connection connection = ConnectionUtil.getConnection(); 
        	PreparedStatement statement = connection.prepareStatement(queryToInsertEmployee)) {
            statement.setInt(1, employee.getId());
            statement.setString(2, employee.getFirstName());
            statement.setString(3, employee.getLastName());
            statement.setString(4, employee.getMobileNumber());
            statement.setString(5, employee.getEmail());
            statement.setDate(6, Date.valueOf(employee.getJoiningDate()));
            statement.setBoolean(7, employee.getActiveStatus());
            return statement.executeUpdate();
            
           
           
        } catch (SQLException e) {
        	logger.error("DB error while creating employee: {}",employee,e);
            throw new EmployeeDaoException("Database error to create employee",e);
        }
        
    }

    public List<Employee> getAllEmployees() throws EmployeeDaoException{
        List<Employee> employees = new ArrayList<>();
        String queryToGetAllEmployees = GET_ALL_EMPLOYEES;
        logger.info("Retrieving data of all employees");
        try (Connection connection = ConnectionUtil.getConnection(); 
        		Statement statement = connection.createStatement(); 
        		ResultSet rs = statement.executeQuery(queryToGetAllEmployees)) {
            while (rs.next()) {
                Employee emp = new Employee(
                        rs.getInt("emp_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("mobile_number"),
                        rs.getString("email"),
                        rs.getDate("joining_date").toLocalDate(),
                        rs.getBoolean("active_status")
            );employees.add(emp);
            
            
            }
        } catch (SQLException e) {
        	logger.error("DB error while retrieving employee data");
        	throw new EmployeeDaoException("No employees in database",e);
        }

        return employees;
    }

    public Employee getEmployeeById(int empId) throws  EmployeeDaoException{
        String queryToGetEmployeeById = GET_EMPLOYEE_BY_ID;
        logger.info("Retrieving employee data with id {}",empId);
        try (Connection connection = ConnectionUtil.getConnection();
        		PreparedStatement statement = connection.prepareStatement(queryToGetEmployeeById)) {
            statement.setInt(1, empId);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return new Employee(
                            rs.getInt("emp_id"),
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getString("mobile_number"),
                            rs.getString("email"),
                            rs.getDate("joining_date").toLocalDate(),
                            rs.getBoolean("active_status")
                    );
                }
            }
        } catch (SQLException e) {
        	logger.error("Database error while retrieving employee with ID {}",empId);
        	throw new EmployeeDaoException("No employee in database with given id",e);
        }
        return null; 
    }

    public int deleteEmployee(int empId) throws EmployeeDaoException{
        String queryToDeleteEmployee = DELETE_EMPLOYEE;
        logger.info("Deleting employee with ID {}",empId, queryToDeleteEmployee, queryToDeleteEmployee, queryToDeleteEmployee, queryToDeleteEmployee, queryToDeleteEmployee, queryToDeleteEmployee, queryToDeleteEmployee, queryToDeleteEmployee, queryToDeleteEmployee);
        try (Connection connection = ConnectionUtil.getConnection();
        		PreparedStatement statement = connection.prepareStatement(queryToDeleteEmployee)) {
            statement.setInt(1, empId);
       
            return statement.executeUpdate();
            
        } catch (SQLException e) {
        	logger.error("Database error while deleting employee with ID {}",empId);
        	throw new EmployeeDaoException("No employee in database with given id",e);
        }
    }

    public int updateEmployee(Employee emp) throws EmployeeDaoException{
        String queryToUpdateEmployee = UPDATE_EMPLOYEE;
        logger.info("Updating employee with ID {}",emp.getId());
        try (Connection connection = ConnectionUtil.getConnection();
        		PreparedStatement statement = connection.prepareStatement(queryToUpdateEmployee)) {
        	statement.setString(1, emp.getFirstName());
        	statement.setString(2, emp.getLastName());
        	statement.setString(3, emp.getMobileNumber());
        	statement.setString(4, emp.getEmail());
        	statement.setDate(5, Date.valueOf(emp.getJoiningDate()));
        	statement.setBoolean(6, emp.getActiveStatus());
        	statement.setInt(7, emp.getId());

           
            return statement.executeUpdate();
            
        } catch (SQLException e) {
        	logger.error("Database error while updating employee with ID {}",emp.getId());
        	throw new EmployeeDaoException("No employee in database with given id",e);
        }
    }
}
