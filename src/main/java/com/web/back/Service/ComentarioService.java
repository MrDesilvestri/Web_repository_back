package com.web.back.Service;
import java.util.List;
import java.util.Optional;

import com.web.back.Entities.Cancha;
import com.web.back.Entities.Comentario;

public interface ComentarioService {
    List<Comentario> listAll();
    
    Comentario guardarComentario(Comentario comentario);
    
    Comentario listById(int id);
    
    void eliminarComentario(int id);
    
    Comentario actualizarComentario(Comentario comentario);
    
}
