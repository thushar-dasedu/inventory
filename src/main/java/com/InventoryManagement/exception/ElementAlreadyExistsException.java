package com.InventoryManagement.exception;

public class ElementAlreadyExistsException extends RuntimeException{
    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public ElementAlreadyExistsException(){
    }
public ElementAlreadyExistsException(String message){
        this.message=message;
}
}
