package com.example.taskmanagerapi.Models.Exceptions;

public class InvalidUserIdException extends RuntimeException {
    public InvalidUserIdException() {
        super("Invalid user id");
    }
}
