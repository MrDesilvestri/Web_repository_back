package com.back.demo.Model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "reserva")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
<<<<<<<< HEAD:src/main/java/com/web/back/Entities/Reserva.java
    @NotNull(message = "User is mandatory")
    @JoinColumn(name = "usuario_id")
    private User usuario;
========
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
>>>>>>>> 2e585ce734625f71ce3761ab9e3fdce60c04cd62:src/main/java/com/back/demo/Model/Reserva.java

    @ManyToOne
    @NotNull(message = "cancha is mandatory")
    @JoinColumn(name = "cancha_id")
    private Cancha cancha;

<<<<<<<< HEAD:src/main/java/com/web/back/Entities/Reserva.java
    @NotNull(message = "fechaInicio is mandatory")
    @Column(name = "fecha_inicio")
    private LocalDateTime fechaInicio;

    @NotNull(message = "fechaFin is mandatory")
    @Column(name = "fecha_fin")
========
    private LocalDateTime fechaInicio;
>>>>>>>> 2e585ce734625f71ce3761ab9e3fdce60c04cd62:src/main/java/com/back/demo/Model/Reserva.java
    private LocalDateTime fechaFin;
}
