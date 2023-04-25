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
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @NotNull(message = "User is mandatory")
    @JoinColumn(name = "usuario_id")
    private User usuario;

    @ManyToOne
    @NotNull(message = "cancha is mandatory")
    @JoinColumn(name = "cancha_id")
    private Cancha cancha;

    @NotNull(message = "fechaInicio is mandatory")
    @Column(name = "fecha_inicio")
    private LocalDateTime fechaInicio;

    @NotNull(message = "fechaFin is mandatory")
    @Column(name = "fecha_fin")
    private LocalDateTime fechaFin;
}
