package com.web.back.Exceptions.ReservaExceptions;

public class ReservaNotFoundException extends RuntimeException {
    public ReservaNotFoundException(String message, int id) {
        super(message);
    }
}
