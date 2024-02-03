package com.InventoryManagement.exception;

public class MobileNumberAndEmailExistsException extends RuntimeException{
private String message;

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public MobileNumberAndEmailExistsException(){}
    public MobileNumberAndEmailExistsException(String message){
        super(message);
        this.message=message;
    }
}
