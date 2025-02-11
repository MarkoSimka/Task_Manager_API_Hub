package com.example.taskmanagerapi.Models.Exceptions;

public class InvalidTaskIdException extends RuntimeException {
    public InvalidTaskIdException() {
        super("Invalid task id");
    }
}
