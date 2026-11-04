package com.tecsup.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class TareaDTO {

    @NotNull(message = "El ID de la campaña es obligatorio")
    @Positive(message = "El ID de la campaña debe ser un número positivo")
    private Long idCampana;

    @NotBlank(message = "La descripción de la tarea es obligatoria")
    private String descripcion;

    @NotBlank(message = "El responsable de la tarea es obligatorio")
    private String responsable;

    private String estado;

    public TareaDTO() {
    }

    public TareaDTO(Long idCampana, String descripcion, String responsable, String estado) {
        this.idCampana = idCampana;
        this.descripcion = descripcion;
        this.responsable = responsable;
        this.estado = estado;
    }

    public Long getIdCampana() {
        return idCampana;
    }

    public void setIdCampana(Long idCampana) {
        this.idCampana = idCampana;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
