package com.litmus7.employeemanager.dto;

import java.time.LocalDate;

public class Employee {
    private int id;
    private String firstName;
    private String lastName;
    private String mobileNumber;
    private String email;
    private LocalDate joiningDate;
    private boolean active;

    public Employee(int id, String firstName, String lastName, String mobileNumber, String email, LocalDate joiningDate, boolean active) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobileNumber = mobileNumber.trim();
        this.email = email;
        this.joiningDate = joiningDate;
        this.active = active;
    }

    public int getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getMobileNumber() { return mobileNumber.trim(); }
    public String getEmail() { return email; }
    public LocalDate getJoiningDate() { return joiningDate; }
    public boolean getActiveStatus() { return active; }

  
    public String toString() {
        return "ID: " + id + ", FirstName: " + firstName + ",LastName: "+lastName+", Email: " + email +
               ", Mobile: " + mobileNumber + 
               ", Date of Joining: " + joiningDate + ", Active: " + active;
    }

    

   
}
