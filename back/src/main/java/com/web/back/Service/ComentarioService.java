package com.web.back.Service;
import java.util.List;
import java.util.Optional;
import com.web.back.Entities.Comentario;

public interface ComentarioService {
    
    public Comentario guardarComentario(Comentario comentario);
    
    public Optional<Comentario> buscarComentarioPorId(int id);
    
    public List<Comentario> buscarTodosLosComentarios();
    
    public void eliminarComentario(long id);
    
    public Comentario actualizarComentario(long id, Comentario comentario);
    
}
