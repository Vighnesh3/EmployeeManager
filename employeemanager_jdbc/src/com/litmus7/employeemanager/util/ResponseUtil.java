package com.litmus7.employeemanager.util;



public class ResponseUtil<D> {
    private String status;   
    private String message; 
    private String code;
    private D data;          

    public ResponseUtil(String status,String code, String message, D data) {
        this.status = status;
        this.code=code;
        this.message = message;
        this.data = data;
        System.out.println(status+"\n"+code+"\n"+message);
       

    }
    public String getStatus() {
        return status;
    }
    public String getCode() {
    	return code;
    }
    public String getMessage() {
        return message;
    }
    public D getData() {
        return data;
    }


    

}
