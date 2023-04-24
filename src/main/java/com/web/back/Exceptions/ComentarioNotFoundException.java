package com.web.back.Exceptions;

public class ComentarioNotFoundException extends RuntimeException {

    public ComentarioNotFoundException(Long comentarioId) {
        super("No se encontró el comentario con ID: " + comentarioId);
    }
}
