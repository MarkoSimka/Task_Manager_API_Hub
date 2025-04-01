package com.example.taskmanagerapi.Models.Exceptions;

public class InvalidUsernameException extends RuntimeException {
    public InvalidUsernameException() {
        super("Invalid username");
    }
}
