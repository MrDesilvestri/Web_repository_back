package com.web.back.Exceptions.UserExceptions;

public class UsuarioFoundException extends RuntimeException{
    public UsuarioFoundException(long id) {
        super("Usuario con id: " + id + " encontrado");
    }
    public UsuarioFoundException(String id) {
        super("Usuario con correo: " + id + " encontrado, no se puede crear otro usuario con el mismo correo");
    }

}
