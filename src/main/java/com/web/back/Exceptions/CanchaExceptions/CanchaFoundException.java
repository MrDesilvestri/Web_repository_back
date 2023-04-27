package com.web.back.Exceptions.CanchaExceptions;


public class CanchaFoundException extends RuntimeException{
    public CanchaFoundException(long message) {
        super("se encontró la cancha con ID: " + message + " No se puede agregar otra cancha con el mismo ID");
    }
}
