package com.tecsup.dto;

import jakarta.validation.constraints.NotBlank;

public class ActualizarEstadoDTO {

    @NotBlank(message = "El nuevo estado es obligatorio")
    private String estado;

    public ActualizarEstadoDTO() {
    }

    public ActualizarEstadoDTO(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
