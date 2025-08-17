
package com.litmus7.employeemanager.util;


import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ValidationUtil {

   
    public static boolean isValidId(int id) {
        return id > 0;
    }

    public static boolean isValidName(String name) {
        return name != null && !name.trim().isEmpty();
    }

    public static boolean isValidMobile(String mobile) {
        return mobile != null && mobile.matches("\\d{10}");
    }

    public static boolean isValidEmail(String email) {
        return email != null && email.matches("^[\\w.-]+@[\\w.-]+\\.\\w+$");
    }

   
    	 public static boolean isValidDate(LocalDate localDate) {
    	        return localDate != null;
    }
}
