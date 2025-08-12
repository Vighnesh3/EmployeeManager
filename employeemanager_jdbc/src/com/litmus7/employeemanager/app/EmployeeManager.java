package com.litmus7.employeemanager.app;

import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import com.litmus7.employeemanager.controller.EmployeeController;

import com.litmus7.employeemanager.dto.Employee;
import com.litmus7.employeemanager.dao.EmployeeDAO;
import com.litmus7.employeemanager.util.ValidationUtil;
import com.litmus7.employeemanager.util.ResponseUtil;
import static com.litmus7.employeemanager.constants.MessageConstants.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
public class EmployeeManager {

    public static void printData(List<Employee> empl) {
        System.out.println("Employee Details:\n");
        for (Employee i : empl) {
            System.out.println(i.getId()+"\t"+i.getFirstName()+"\t\t   "+i.getLastName()+"\t"+i.getMobileNumber()+"\t"+i.getEmail()+"\t\t"+i.getJoiningDate()+"\t"+i.getActiveStatus());
        }
    }

    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        EmployeeController controller = new EmployeeController();
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
                	System.out.println("Enter employee details to add to DB:");
                    System.out.print("ID: ");
                    int id = sc.nextInt(); 
                    sc.nextLine();
                    if (!ValidationUtil.isValidId(id)) {
                        System.out.println(INVALID_ID);
                        return;
                    }
                    System.out.print("First Name: ");
                    String fname = sc.nextLine().trim();
                    if (!ValidationUtil.isValidName(fname)) {
                        System.out.println(INVALID_FIRSTNAME);
                        return;
                    }
                    System.out.print("Last Name: ");
                    String lname = sc.nextLine().trim();
                    if (!ValidationUtil.isValidName(lname)) {
                        System.out.println(INVALID_LASTNAME);
                        return;
                    }
                    System.out.print("Mobile Number: ");
                    String mobile = sc.nextLine().trim();
                    if (!ValidationUtil.isValidMobile(mobile)) {
                        System.out.println(INVALID_MOBILE);
                        return;
                    }
                    System.out.print("Email: ");
                    String email = sc.nextLine().trim();
                    if (!ValidationUtil.isValidEmail(email)) {
                        System.out.println(INVALID_EMAIL);
                        return;
                    }
                    System.out.print("Joining Date (yyyy-mm-dd): ");
                    LocalDate jd = LocalDate.parse(sc.nextLine());
                    if (jd == null) {
                        System.out.println(INVALID_DATE_FORMAT);
                        return;
                    }
                    System.out.print("Active (true/false): ");
                    boolean active = sc.nextBoolean();

                    Employee employee = new Employee(id, fname, lname, mobile, email, jd, active);
                    controller.createEmployee(employee);
                    
                    
                }
                case 2 -> {
                	ResponseUtil<?> response = controller.getAllEmployees();
                    
                    if (response.getData() != null) {
                    	System.out.println("\n--- Employee List ---");
                        for (Employee employee : (List<Employee>)response.getData()) {
                            System.out.println(employee); 
                        }
                    }
                }
                   
                
                case 3 -> {
                	
                	System.out.println("Enter ID of employee to retrieve : ");
                	int id=sc.nextInt();
                	
                	ResponseUtil<Employee> response = controller.getEmployeeById(id);
                	Employee employee = response.getData();

                	if (employee != null) {
                	    System.out.println("\n--- Employee Details ---");
                	    System.out.println(employee);
                	} 
}
                case 4 -> {
                    System.out.print("Enter Employee ID to delete: ");
                    int empId = sc.nextInt();
                    controller.deleteEmployee(empId);
                }
                case 5 -> {
                	System.out.println("Enter updated details:");
                    System.out.print("ID: ");
                    
                    int id = sc.nextInt(); sc.nextLine();
                    if (!ValidationUtil.isValidId(id)) {
                        System.out.println(INVALID_ID);
                        return;
                    }
                    System.out.print("First Name: ");
                    String fname = sc.nextLine().trim();
                    if (!ValidationUtil.isValidName(fname)) {
                        System.out.println(INVALID_FIRSTNAME);
                        return;
                    }
                    System.out.print("Last Name: ");
                    String lname = sc.nextLine().trim();
                    if (!ValidationUtil.isValidName(lname)) {
                        System.out.println(INVALID_LASTNAME);
                        return;
                    }
                    System.out.print("Mobile Number: ");
                    String mobile = sc.nextLine().trim();
                    if (!ValidationUtil.isValidMobile(mobile)) {
                        System.out.println(INVALID_MOBILE);
                        return;
                    }
                    System.out.print("Email: ");
                    String email = sc.nextLine().trim();
                    if (!ValidationUtil.isValidEmail(email)) {
                        System.out.println(INVALID_EMAIL);
                        return;
                    }
                    System.out.print("Joining Date (yyyy-mm-dd): ");
                    LocalDate jd = LocalDate.parse(sc.nextLine());
                    if (jd == null) {
                        System.out.println(INVALID_DATE_FORMAT);
                        return;
                    }
                    System.out.print("Active (true/false): ");
                    boolean active = sc.nextBoolean();

                    Employee employee = new Employee(id, fname, lname, mobile, email, jd, active);
                	controller.updateEmployee(employee);
                	
                   
                }
                case 6 -> {
                    System.out.println("Exiting program...");
                    sc.close();
                    return;
                }
                
                
                
                 
                default -> System.out.println("Invalid choice");
            }
        
    }}catch (InputMismatchException e) {
        System.out.println("Please enter a valid number.");
        sc.nextLine(); 
    } catch (NoSuchElementException e) {
        System.out.println("No input received. Exiting...");
        
    }
}}
