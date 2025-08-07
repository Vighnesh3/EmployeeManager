package com.litmus7.employeemanager.dao;
import com.litmus7.employeemanager.dto.Employee;
import com.litmus7.employeemanager.util.ConnectionUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    
    public int createEmployee(Employee employee) {
        String queryToInsertEmployee = "INSERT INTO employee (emp_id, first_name, last_name, mobile_number, email, joining_date, active_status) VALUES (?, ?, ?, ?, ?, ?, ?)";
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
            e.printStackTrace();
        }
        return 0;
    }

    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        String queryToGetAllEmployees = "SELECT emp_id,first_name,last_name,mobile_number,email,joining_date,active_status FROM employee";

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
            e.printStackTrace();
        }

        return employees;
    }

    public Employee getEmployeeById(int empId) {
        String queryToGetEmployeeById = "SELECT emp_id,first_name,last_name,mobile_number,email,joining_date,active_status FROM employee WHERE emp_id = ?";
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
            e.printStackTrace();
        }
        return null; 
    }

    public int deleteEmployee(int empId) {
        String queryToDeleteEmployee = "DELETE FROM employee WHERE emp_id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
        		PreparedStatement statement = connection.prepareStatement(queryToDeleteEmployee)) {
            statement.setInt(1, empId);
            int rowsAffected;
            return rowsAffected = statement.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }return 0;
    }

    public int updateEmployee(Employee emp) {
        String queryToUpdateEmployee = "UPDATE employee SET first_name = ?, last_name = ?, mobile_number = ?, email = ?, joining_date = ?, active_status = ? WHERE emp_id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
        		PreparedStatement statement = connection.prepareStatement(queryToUpdateEmployee)) {
        	statement.setString(1, emp.getFirstName());
        	statement.setString(2, emp.getLastName());
        	statement.setString(3, emp.getMobileNumber());
        	statement.setString(4, emp.getEmail());
        	statement.setDate(5, Date.valueOf(emp.getJoiningDate()));
        	statement.setBoolean(6, emp.getActiveStatus());
        	statement.setInt(7, emp.getId());

            int rowsAffected=0;
            return rowsAffected= statement.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }return 0;
    }
}
