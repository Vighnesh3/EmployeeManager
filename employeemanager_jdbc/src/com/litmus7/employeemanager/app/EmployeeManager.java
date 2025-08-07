package com.litmus7.employeemanager.app;

import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import com.litmus7.employeemanager.controller.EmployeeController;
import com.litmus7.employeemanager.dto.Employee;
import com.litmus7.employeemanager.dao.EmployeeDAO;
import com.litmus7.employeemanager.service.EmployeeService;
import java.time.LocalDate;
import java.util.Date;
public class EmployeeManager {

    public static void printData(List<Employee> empl) {
        System.out.println("Employee Details:\n");
        for (Employee i : empl) {
            System.out.println(i.getId()+"\t"+i.getFirstName()+"\t\t   "+i.getLastName()+"\t"+i.getMobileNumber()+"\t"+i.getEmail()+"\t\t"+i.getJoiningDate()+"\t"+i.getActiveStatus());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        EmployeeController employee = new EmployeeController();
        EmployeeDAO employee1=new EmployeeDAO();
try {
        while (true) {
            System.out.println("\nChoose an option:\n1. Add employee to database\n"
            		+ "2. Print all employees in database\n"
            		+ "3. Print employee with given id\n4. delete employee from database"
            		+ "\n5. update employee details in database\n6. Exit");

            int choice = sc.nextInt();
sc.nextLine();
            switch (choice) { 
                case 1 -> {
                	employee.createEmployee();
                    
                }
                case 2 -> {
                    employee.getAllEmployees();
                   
                }
                case 3 -> {
                	
                	System.out.println("Enter ID of employee to retrieve : ");
                	int id=sc.nextInt();
                	employee.getEmployeeById(id);
                    
                    
                }
                case 4 -> {
                    System.out.print("Enter Employee ID to delete: ");
                    int empId = sc.nextInt();
                    employee.deleteEmployee(empId);
                }
                case 5 -> {
                	employee.updateEmployee();
                   
                }
                case 6 -> {
                    System.out.println("Exiting program...");
                    sc.close();
                    return;
                }
                
                
                
                
                default -> System.out.println("Invalid choice");
            }}
        
    }catch (InputMismatchException e) {
        System.out.println("Please enter a valid number.");
        sc.nextLine(); 
    } catch (NoSuchElementException e) {
        System.out.println("No input received. Exiting...");
        
    }
}}
