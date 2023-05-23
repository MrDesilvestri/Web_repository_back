package com.web.back.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservaPK implements Serializable {
    @Column(name = "usuario_id")
    private Long idUsuario;

    @Column(name = "cancha_id")
    private Long idCancha;
}
