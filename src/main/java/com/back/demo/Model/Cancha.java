package com.back.demo.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "cancha")
public class Cancha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;
    private String descripcion;
    private String ubicacion;
    private BigDecimal precioHora;

    @OneToMany(mappedBy = "cancha", cascade = CascadeType.ALL)
    private List<Reserva> reservas;

    @OneToMany(mappedBy = "cancha", cascade = CascadeType.ALL)
    private List<Comentario> comentarios;

    public Cancha(int i, String nombre, String descripcion, String ubicacion, int precioHora) {
        this.id = i;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.ubicacion = ubicacion;
        this.precioHora = new BigDecimal(precioHora);
    }

}
