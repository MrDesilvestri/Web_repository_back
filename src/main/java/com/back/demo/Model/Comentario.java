package com.back.demo.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "comentario")
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
<<<<<<<< HEAD:src/main/java/com/web/back/Entities/Comentario.java
    private long id;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private User usuario;
========
    private int id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

>>>>>>>> 2e585ce734625f71ce3761ab9e3fdce60c04cd62:src/main/java/com/back/demo/Model/Comentario.java
    @ManyToOne
    @JoinColumn(name = "cancha_id")
    private Cancha cancha;

    private String comentario;
}
