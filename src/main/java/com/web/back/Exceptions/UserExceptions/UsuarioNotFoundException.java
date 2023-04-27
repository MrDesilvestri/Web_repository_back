package com.web.back.Exceptions.UserExceptions;

public class UsuarioNotFoundException extends RuntimeException{
    public UsuarioNotFoundException(long message) {
        super("No se encontró el usuario con ID: " +  message );
    }
}
