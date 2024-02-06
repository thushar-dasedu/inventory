package com.InventoryManagement.exception;

public class NotNullException extends RuntimeException{
    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public NotNullException(){}
    public NotNullException(String message){
        this.message=message;
    }
}
