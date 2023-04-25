package com.web.back.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "cancha")
public class Cancha {
    @Id
    @Column(name = "id")

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "name is mandatory")
    @Size(min = 5, max = 30)
    @Column(name = "nombre")
    private String nombre;

    @NotNull(message = "descripcion is mandatory")
    @Size( max = 250)
    @Column(name = "descripcion")
    private String descripcion;

    @NotNull(message = "ubicacion is mandatory")
    @Size(max = 100)
    @Column(name = "ubicacion")
    private String ubicacion;

    @NotNull(message = "precio_hora is mandatory")
    @Digits(integer = 5, fraction = 2)
    @Column(name = "precio_hora")
    private BigDecimal precioHora;
}
