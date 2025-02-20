package com.example.taskmanagerapi.Models.Exceptions;

public class InvalidRoleIdException extends RuntimeException {
    public InvalidRoleIdException() {
        super("Invalid role id");
    }
}
