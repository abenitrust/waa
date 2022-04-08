package com.waa.lab.exception;

public class NewUserException extends Exception{
    public NewUserException(String msg) {
        super(msg);
    }

    public NewUserException() {
        this("Can't create new user, please check and try again!");
    }
}
