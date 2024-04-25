package com.example.pokemoin.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(PokemonNotFoundEx.class)
    public ResponseEntity<ErrorObject> handlePokemonNotFoundException(PokemonNotFoundEx ex, WebRequest request){
        ErrorObject errorObject= new ErrorObject();

        errorObject.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorObject.setMessage(ex.getMessage());
        errorObject.setTimestamp(new Date());
        return  new ResponseEntity<ErrorObject>(errorObject, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ReviewNotFoundEx.class)
    public ResponseEntity<ErrorObject> handleRevireNotFoundException(PokemonNotFoundEx ex, WebRequest request){
        ErrorObject errorObject = new ErrorObject();
        errorObject.setTimestamp(new Date());
        errorObject.setMessage(ex.getMessage());
        errorObject.setStatusCode(HttpStatus.NOT_FOUND.value());
        return  new ResponseEntity<ErrorObject>(errorObject, HttpStatus.NOT_FOUND);
    }
}
