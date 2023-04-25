package com.web.back.Entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "comentario")
public class Comentario {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private User usuario;
    @ManyToOne
    @JoinColumn(name = "cancha_id")
    private Cancha cancha;
    @Column(name = "comentario")
    private String comentario;
}
