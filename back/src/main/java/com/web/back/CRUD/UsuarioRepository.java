package com.web.back.CRUD;
import java.util.List;
import org.springframework.data.repository.Repository;

import com.web.back.Entities.Usuario;

public interface UsuarioRepository extends Repository<Usuario, Integer>{
    List <Usuario> findAll();
    Usuario findOne(int id);
    Usuario save(Usuario usuario);
    void update(Usuario usuario);
    Usuario findByEmail(String email);
    void delete(Usuario usuario);
}
