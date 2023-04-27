package com.web.back.Entities;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;


@Entity
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "usuario")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "id")
    private long id;

    @NotBlank
    @Size(min = 3, max = 20)
    @Column(name = "nombre")
    private String nombre;

    @NotBlank
    @Size(min = 3, max = 20)
    @Column(name = "email")
    private String email;

    @NotBlank
    @Column(name = "telefono")
    @Size(min = 10, max = 15)
    private Long telefono;

    @NotBlank
    @Size(min = 3, max = 20)
    @Column(name = "password")
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Reserva> reservas;

    @JsonIgnore
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Comentario> comentarios;
}
