package com.InventoryManagement.exception;

public class CustomerAlreadyExistsException extends RuntimeException{
    private String message;
    public CustomerAlreadyExistsException(){}

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public CustomerAlreadyExistsException(String message){
        super(message);
        this.message=message;
    }
}
