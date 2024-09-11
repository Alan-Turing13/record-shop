package com.northcoders.record_shop.advice;

import com.northcoders.record_shop.exception.InvalidInputException;
import com.northcoders.record_shop.exception.InvalidRequestException;
import com.northcoders.record_shop.exception.NotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Set;

@ControllerAdvice
public class CustomExceptionHandler {

    private static final String INVALID_INPUT_MSG =
            "One or more of your fields was invalid. \nPlease check your input.";

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleConstraintViolation(ConstraintViolationException c){
        Set<ConstraintViolation<?>> constraintViolations = c.getConstraintViolations();
        StringBuilder sb = new StringBuilder();
        for (var constraint: constraintViolations){
            sb.append(constraint.getMessage() + "\n");
        }
        return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleInvalidGenre(HttpMessageNotReadableException h){
        return new ResponseEntity<>(INVALID_INPUT_MSG, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleInvalidInputType(MethodArgumentNotValidException m){
        return new ResponseEntity<>(INVALID_INPUT_MSG, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException i){
        return new ResponseEntity<>(
                "That method isn't allowed at that endpoint.", HttpStatus.METHOD_NOT_ALLOWED
        );
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<String> handleInvalidMethod(HttpRequestMethodNotSupportedException h){
        return new ResponseEntity<>(
                "That method isn't allowed at that endpoint.", HttpStatus.METHOD_NOT_ALLOWED
        );
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<String> handleInvalidGenre(HttpMediaTypeNotSupportedException h){
        return new ResponseEntity<>(
                "Your form input was invalid, please try a JSON object", HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(NotFoundException nfe){
        return new ResponseEntity<>(nfe.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<String> handleInvalidInputException(InvalidInputException iie){
        return new ResponseEntity<>(iie.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<String>  handleInvalidMethodArgument(InvalidRequestException ire) {
        return new ResponseEntity<>(ire.getMessage(), HttpStatus.BAD_REQUEST);
    }


}
