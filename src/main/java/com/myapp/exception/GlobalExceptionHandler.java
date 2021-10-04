package com.myapp.exception;

import com.mongodb.MongoException;
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

    @ExceptionHandler(MenuItemException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionDTO menuItemException(MenuItemException ex) {
        return new ExceptionDTO(HttpStatus.NOT_FOUND.value(),ex.getMessage());
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
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionDTO internalServerError(Exception ex) {
        return new ExceptionDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(),buildMyException(ex.getMessage()));
    }

    @ExceptionHandler(MongoException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionDTO internalServerErrorMongo(MongoException ex) {
        return new ExceptionDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(),buildMyException(ex.getMessage()));
    }

    private String buildMyException(String message)
    {

        return  "my Exception :  " + message ;
    }

}
