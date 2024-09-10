package com.northcoders.record_shop.advice;

import com.northcoders.record_shop.exception.InvalidInputException;
import com.northcoders.record_shop.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(NotFoundException nfe){
        return new ResponseEntity<>(nfe.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<String> handleInvalidInputException(InvalidInputException iie){
        return new ResponseEntity<>(iie.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public Map<String, String> handleInvalidInput(MethodArgumentNotValidException exception){
//        Map<String, String> errorMessage = new HashMap<>();
//        exception.getBindingResult().getFieldErrors().forEach(fieldError -> {
//            errorMessage.put(fieldError.getField(), fieldError.getDefaultMessage());
//        });
//        return errorMessage;
//    }
//
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(HttpMessageNotReadableException.class)
//    public Map<String, String> handleInvalidGenre(HttpMessageNotReadableException exception){
//        Map<String, String> errorMessage = new HashMap<>();
//        errorMessage.put("Error", exception.getMessage());
//        return errorMessage;
//    }
//
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    @ExceptionHandler(NotFoundException.class)
//    public Map<String, String> handleNotFound(NotFoundException e){
//        Map<String, String> errorMessage = new HashMap<>();
//        errorMessage.put("Error", e.getMessage());
//        return errorMessage;
//    }
//
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    @ExceptionHandler(TemplateInputException.class)
//    public Map<String, String> templateInput(TemplateInputException e){
//        Map<String, String> errorMessage = new HashMap<>();
//        errorMessage.put("Error", e.getMessage());
//        return errorMessage;
//    }

}
