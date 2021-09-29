package com.myapp.exception;

import org.springframework.boot.json.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FoodItemException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionDTO foodItemNotFoundException(FoodItemException ex) {
        return new ExceptionDTO(HttpStatus.NOT_FOUND.value(),buildMyException(ex.getMessage()));
    }

    @ExceptionHandler(HttpClientErrorException.UnprocessableEntity.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ExceptionDTO unprocessableEntity(FoodItemException ex) {
        return new ExceptionDTO(HttpStatus.NOT_FOUND.value(),buildMyException(ex.getMessage()));
    }

    @ExceptionHandler(JsonParseException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionDTO jsonParseException(FoodItemException ex) {
        return new ExceptionDTO(HttpStatus.NOT_FOUND.value(), buildMyException(ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionDTO internalServerError(Exception ex) {
        return new ExceptionDTO(HttpStatus.NOT_FOUND.value(),buildMyException(ex.getMessage()));
    }

    private String buildMyException(String message)
    {

        return  "my Exception :  " + message ;
    }

}
