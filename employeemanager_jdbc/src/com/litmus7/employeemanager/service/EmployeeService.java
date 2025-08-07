package com.litmus7.employeemanager.service;

import com.litmus7.employeemanager.dao.EmployeeDAO;
import com.litmus7.employeemanager.dto.Employee;
import com.litmus7.employeemanager.util.ResponseUtil;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class EmployeeService {
    private final EmployeeDAO dao = new EmployeeDAO();
    Scanner sc=new Scanner(System.in);
    
    public ResponseUtil<String> createEmployee() {
    	
    	System.out.println("Enter employee details to add to DB:");
        System.out.print("ID: ");
        int id = sc.nextInt(); 
        sc.nextLine();
        System.out.print("First Name: ");
        String fname = sc.nextLine();
        System.out.print("Last Name: ");
        String lname = sc.nextLine();
        System.out.print("Mobile Number: ");
        String mobile = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Joining Date (yyyy-mm-dd): ");
        LocalDate jd = LocalDate.parse(sc.nextLine());
        System.out.print("Active (true/false): ");
        boolean active = sc.nextBoolean();

        Employee employee = new Employee(id, fname, lname, mobile.trim(), email, jd, active);
        
        int rows = dao.createEmployee(employee);
        return rows > 0 ?(
        	
        
                new ResponseUtil<>("SUCCESS", "Employee created", null) ):
                new ResponseUtil<>("FAIL", "Employee creation failed", null);
    }

    public ResponseUtil<List<Employee>> getAllEmployees() {
        List<Employee> employees = dao.getAllEmployees();
        if (employees.isEmpty()) {
            return new ResponseUtil<>("FAIL", "No employees found in database", employees);
        }
        if (employees != null && !employees.isEmpty()) {
            System.out.println("\n--- Employee List ---");
            for (Employee employee : employees) {
                System.out.println(employee); 
            }
        } 
        return new ResponseUtil<>("SUCCESS", "Employee data retrieved", employees);
    }
    public ResponseUtil<Employee> getEmployeeById(int id) {
        Employee employee = dao.getEmployeeById(id);
        if (employee == null) {
        	  
            return new ResponseUtil<>("FAIL", "No employee found with given ID", null);
          
        }
        else{
            System.out.println("\n--- Employee Details ---");
             {
                System.out.println(employee); 
            }
        }  {
            
        }
        return new ResponseUtil<>("SUCCESS", "Employee data retrieved", employee);
    }

    public ResponseUtil<String> deleteEmployee(int id) {
        int rows = dao.deleteEmployee(id);
        return rows > 0 ?
                new ResponseUtil<>("SUCCESS", "Employee deleted with given ID", null) :
                new ResponseUtil<>("FAIL", "Employee not found with given ID", null);
    }

    public ResponseUtil<String> updateEmployee() {
    	System.out.println("Enter updated details:");
        System.out.print("ID: ");
        int id = sc.nextInt(); sc.nextLine();
        System.out.print("First Name: ");
        String fn = sc.nextLine();
        System.out.print("Last Name: ");
        String ln = sc.nextLine();
        System.out.print("Mobile Number: ");
        String mob = sc.nextLine().trim();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Joining Date (yyyy-mm-dd): ");
        LocalDate jd = LocalDate.parse(sc.nextLine());
        System.out.print("Active (true/false): ");
        boolean active = sc.nextBoolean();

        Employee employee = new Employee(id, fn, ln, mob, email, jd, active);
        int rows = dao.updateEmployee(employee);
        return rows > 0 ?
                new ResponseUtil<>("SUCCESS", "Employee data updated", null) :
                new ResponseUtil<>("FAIL", "No employee found with given ID", null);
    }

    
   
}
