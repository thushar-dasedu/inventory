package com.InventoryManagement.exception;

public class BadQuantityAssignException extends RuntimeException{
    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public BadQuantityAssignException(){
    }
    public BadQuantityAssignException(String message){
        this.message=message;
    }
}
