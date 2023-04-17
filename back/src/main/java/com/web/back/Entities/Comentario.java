package com.web.back.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "comentario")
public class Comentario {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String comentario;
    
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    
    @ManyToOne
    @JoinColumn(name = "cancha_id")
    private Cancha cancha;
    
    // constructor vacío
    public Comentario() {}

    // constructor con parámetros
    public Comentario(String comentario, Usuario usuario, Cancha cancha) {
        this.comentario = comentario;
        this.usuario = usuario;
        this.cancha = cancha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Cancha getCancha() {
        return cancha;
    }

    public void setCancha(Cancha cancha) {
        this.cancha = cancha;
    }
    
    
}
