package com.web.back.DTO;

import lombok.Data;

import java.time.LocalDate;
@Data
public class ReservaDTO {
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int usuarioId;
    private int canchaId;

    public ReservaDTO(LocalDate fechaInicio, LocalDate fechaFin, int usuarioId, int canchaId) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.usuarioId = usuarioId;
        this.canchaId = canchaId;
    }
}
