package com.northcoders.record_shop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InvalidInputException extends Exception{
    public InvalidInputException(String message){
        super(message);
    }
}
