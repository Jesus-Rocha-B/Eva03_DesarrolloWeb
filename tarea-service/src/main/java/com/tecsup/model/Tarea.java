package com.tecsup.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tarea")
public class Tarea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long idCampana;

    @Column(nullable = false, length = 255)
    private String descripcion;

    @Column(nullable = false, length = 100)
    private String responsable;

    @Column(nullable = false, length = 30)
    private String estado = "PENDIENTE";

    @Column(nullable = false)
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    public Tarea() {
    }

    public Tarea(Long id, Long idCampana, String descripcion, String responsable, String estado, LocalDateTime fechaCreacion) {
        this.id = id;
        this.idCampana = idCampana;
        this.descripcion = descripcion;
        this.responsable = responsable;
        this.estado = (estado != null) ? estado : "PENDIENTE";
        this.fechaCreacion = (fechaCreacion != null) ? fechaCreacion : LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
