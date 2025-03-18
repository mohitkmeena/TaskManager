package com.mohit.TaskManager.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GLobalExceptionHandler {

    @ExceptionHandler(UserNotFound.class)
    public ProblemDetail handleUserNotFound(UserNotFound ex){
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,ex.getMessage());
    }

}
