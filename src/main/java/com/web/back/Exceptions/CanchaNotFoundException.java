package com.web.back.Exceptions;

public class CanchaNotFoundException extends  RuntimeException{
    public CanchaNotFoundException(String message) {
        super("No se encontro la cancha con el ID : " + message);
    }
}
