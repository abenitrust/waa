package com.waa.lab.exception;

public class InvalidUserCredentialException extends Exception{
    public InvalidUserCredentialException(String errorMessage) {
        super(errorMessage);
    }

    public InvalidUserCredentialException(){
        this("Invalid credentials, user not found.");
    }
}
