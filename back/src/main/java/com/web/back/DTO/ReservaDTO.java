package com.web.back.DTO;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ReservaDTO {
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private int usuarioId;
    private int canchaId;

    public ReservaDTO(LocalDateTime fechaInicio, LocalDateTime fechaFin, int usuarioId, int canchaId) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.usuarioId = usuarioId;
        this.canchaId = canchaId;
    }
}
