package com.waa.lab.exception;

public class InvalidRoleException extends Exception{
    public InvalidRoleException(String msg, String role) {
        super(msg);
    }

    public InvalidRoleException(String role) {
        this("Error: Invalid role '" + role + "'", role);
    }

    public InvalidRoleException() {
        this("Error: Invalid role");
    }
}
