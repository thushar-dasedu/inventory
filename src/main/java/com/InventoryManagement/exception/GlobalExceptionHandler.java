package com.InventoryManagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {
@ExceptionHandler(value =CustomerAlreadyExistsException.class )
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ErrorResponse handleException(CustomerAlreadyExistsException ex){
    return new ErrorResponse(HttpStatus.CONFLICT.value(),ex.getMessage());
}
@ExceptionHandler(value = MobileNumberAndEmailExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ErrorResponse handleEmailMobileExists(MobileNumberAndEmailExistsException ex){
    return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
}
@ExceptionHandler(value = MobileNumberNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ErrorResponse handelMobileNumberNotFound(MobileNumberNotFoundException ex){
    return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
}
@ExceptionHandler(value = NoSuchElementException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ErrorResponse handelNoSuchException(NoSuchElementException ex){
    return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
}
@ExceptionHandler(value = ElementAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handelElementAlreadyExists(ElementAlreadyExistsException ex){
    return new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage());
}
@ExceptionHandler(value = BadQuantityAssignException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleBadQuantityAssign(BadQuantityAssignException e){
    return new ErrorResponse(HttpStatus.BAD_REQUEST.value(),e.getMessage());
}
@ExceptionHandler(value = NotNullException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleNotNullException(NotNullException e){
    return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
}
}
