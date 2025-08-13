package com.litmus7.employeemanager.util;



public class ResponseUtil<D> {
    private String status;   
    private String message; 
    private D data;          

    public ResponseUtil(String status, String message, D data) {
        this.status = status;
        this.message = message;
        this.data = data;
        System.out.println(status+"\n"+message);
       

    }
    public String getStatus() {
        return status;
    }
    public String getMessage() {
        return message;
    }
    public D getData() {
        return data;
    }


    

}
