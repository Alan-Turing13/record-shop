package com.northcoders.record_shop.advice;

import com.northcoders.record_shop.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CustomExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalidInput(MethodArgumentNotValidException exception){
        Map<String, String> errorMessage = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(fieldError -> {
            errorMessage.put(fieldError.getField(), fieldError.getDefaultMessage());
        });
        return errorMessage;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(NotFoundException.class)
    public Map<String, String> handleNotFound(NotFoundException e){
        Map<String, String> errorMessage = new HashMap<>();
        errorMessage.put("Error", e.getMessage());
        return errorMessage;
    }

}
