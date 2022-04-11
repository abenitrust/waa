package com.waa.lab.exception;

public class RefreshTokenException extends Exception{
    public RefreshTokenException(String msg) {
        super(msg);
    }

    public RefreshTokenException() {
        this("Token can't be refreshed");
    }
}
