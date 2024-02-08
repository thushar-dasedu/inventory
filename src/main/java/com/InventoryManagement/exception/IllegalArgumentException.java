package com.InventoryManagement.exception;

public class IllegalArgumentException extends RuntimeException{
    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public IllegalArgumentException(){

    }public IllegalArgumentException(String message){
        this.message=message;
    }
}
