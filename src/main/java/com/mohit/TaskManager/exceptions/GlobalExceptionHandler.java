package com.mohit.TaskManager.exceptions;

import com.mohit.TaskManager.dtos.TaskDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFound.class)
    public ProblemDetail handleUserNotFound(UserNotFound ex){
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,ex.getMessage());
    }
    @ExceptionHandler(TaskNotFoundException.class)
    public ProblemDetail handleUserNotFound(TaskNotFoundException ex){
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,ex.getMessage());
    }

}
