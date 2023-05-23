package com.web.back.Exceptions.UserExceptions;

public class UserPasswordException extends RuntimeException{
    public UserPasswordException(String message) {
        super(message);
    }
}
