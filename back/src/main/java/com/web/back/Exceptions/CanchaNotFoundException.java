package com.web.back.Exceptions;


public class CanchaNotFoundException  extends RuntimeException{
    public CanchaNotFoundException(long message) {
        super("No se encontró la cancha con ID: " +  message );
    }
}
