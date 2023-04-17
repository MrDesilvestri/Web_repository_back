package com.web.back.Service;
import java.util.List;

import com.web.back.Entities.Usuario;
import com.web.back.Entities.Comentario;
public interface ComentarioService{
    //para Comentario

    public void agregarComentario(Comentario comentario);
    public List<Comentario> obtenerComentariosPorCancha(int id);
    public List<Comentario> obtenerComentariosPorUsuario(Usuario usuario);
    public void eliminarComentario(Comentario comentario);
}