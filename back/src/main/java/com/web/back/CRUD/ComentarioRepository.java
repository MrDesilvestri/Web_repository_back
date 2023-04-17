package com.web.back.CRUD;
import java.util.List;
import org.springframework.data.repository.Repository;

import com.web.back.Entities.Comentario;
import com.web.back.Entities.Usuario;
public interface ComentarioRepository extends Repository<Comentario, Integer>{
    List<Comentario>findAll();
    List<Comentario> findByUsuario(Usuario usuario);
    List<Comentario> findByCancha(int id);
    Comentario save(Comentario comentario);
    void delete(Comentario comentario);
}
