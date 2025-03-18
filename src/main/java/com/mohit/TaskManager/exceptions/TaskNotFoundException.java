package com.mohit.TaskManager.exceptions;

public class TaskNotFoundException extends RuntimeException{
    public TaskNotFoundException(String taskNotFound) { super(taskNotFound);
    }
}
