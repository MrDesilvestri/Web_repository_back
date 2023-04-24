package com.web.back.Exceptions;

public class ReservaNotFoundException extends RuntimeException {
    public ReservaNotFoundException(String message, int id) {
        super(message);
    }
}
