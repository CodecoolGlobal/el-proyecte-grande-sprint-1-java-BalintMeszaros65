package com.codecool.procrastination.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.rmi.AlreadyBoundException;
import java.sql.SQLException;
import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalControllerExceptionHandler {


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({SQLException.class, AlreadyBoundException.class, NoSuchElementException.class,
            IllegalArgumentException.class})
    @ResponseBody
    public String handleBadSQLRequest(Throwable exception) {
        return exception.getMessage();
    }

}
