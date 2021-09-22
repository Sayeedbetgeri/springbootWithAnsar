package com.myapp.exception;

import org.springframework.boot.json.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FoodItemException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String foodItemNotFoundException(FoodItemException ex) {
        return buildMyException(ex.getMessage());
    }

    @ExceptionHandler(HttpClientErrorException.UnprocessableEntity.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public String unprocessableEntity(FoodItemException ex) {
        return buildMyException(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String internalServerError(Exception ex) {
        return buildMyException(ex.getMessage());
    }

    @ExceptionHandler(JsonParseException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String jsonParseException(FoodItemException ex) {
        return buildMyException(ex.getMessage());
    }

    private String buildMyException(String message)
    {

        return message + " my Exception";
    }

}
