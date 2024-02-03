package com.InventoryManagement.exception;

public class NoSuchElementException extends RuntimeException{
    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public NoSuchElementException(){}
    public NoSuchElementException(String message){
        this.message=message;
    }
}
