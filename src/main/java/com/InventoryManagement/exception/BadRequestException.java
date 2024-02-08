package com.InventoryManagement.exception;

public class BadRequestException extends RuntimeException{
    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public BadRequestException(){
    }
    public BadRequestException(String message){
        this.message=message;
    }
}
