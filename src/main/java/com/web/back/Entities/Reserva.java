package com.web.back.Entities;


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

    @EmbeddedId
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private ReservaPK id;

    @NotNull(message = "fechaInicio is mandatory")
    @Column(name = "fecha_inicio")
    private LocalDateTime fechaInicio;

    @NotNull(message = "fechaFin is mandatory")
    @Column(name = "fecha_fin")
    private LocalDateTime fechaFin;

    @ManyToOne
    @NotNull(message = "User is mandatory")
    @JoinColumn(name = "usuario_id", insertable = false, updatable = false)
    private User usuario;

    @ManyToOne
    @NotNull(message = "cancha is mandatory")
    @JoinColumn(name = "cancha_id",insertable = false, updatable = false)
    private Cancha cancha;



}
