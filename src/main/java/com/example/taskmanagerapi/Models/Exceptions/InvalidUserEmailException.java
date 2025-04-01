package com.example.taskmanagerapi.Models.Exceptions;

public class InvalidUserEmailException extends RuntimeException {
    public InvalidUserEmailException() {
        super("Invalid user email, please use valid email address and try again");
    }
}
