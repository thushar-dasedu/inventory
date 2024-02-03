package com.InventoryManagement.exception;

public class MobileNumberNotFoundException extends RuntimeException{
    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public MobileNumberNotFoundException(){}
    public MobileNumberNotFoundException(String message){
        this.message=message;
    }
}
