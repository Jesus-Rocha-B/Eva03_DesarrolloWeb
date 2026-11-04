package com.tecsup.services;

import com.tecsup.dto.TareaDTO;
import com.tecsup.exception.ResourceNotFoundException;
import com.tecsup.model.Tarea;
import com.tecsup.repository.TareaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class TareaService {

    private static final List<String> ESTADOS_VALIDOS = Arrays.asList(
            "PENDIENTE", "EN_PROCESO", "COMPLETADA", "CANCELADA"
    );

    private final TareaRepository tareaRepository;

    public TareaService(TareaRepository tareaRepository) {
        this.tareaRepository = tareaRepository;
    }

    public List<Tarea> listarTodas() {
        return tareaRepository.findAll();
    }

    public Tarea buscarPorId(Long id) {
        return tareaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tarea no encontrada con ID: " + id));
    }

    public List<Tarea> listarPorCampana(Long idCampana) {
        return tareaRepository.findByIdCampana(idCampana);
    }

    public List<Tarea> listarPorEstado(String estado) {
        validarEstado(estado);
        return tareaRepository.findByEstado(estado);
    }

    public Tarea crear(TareaDTO dto) {
        String estadoInicial = "PENDIENTE";
        if (dto.getEstado() != null && !dto.getEstado().trim().isEmpty()) {
            validarEstado(dto.getEstado());
            estadoInicial = dto.getEstado().toUpperCase();
        }

        Tarea tarea = new Tarea();
        tarea.setIdCampana(dto.getIdCampana());
        tarea.setDescripcion(dto.getDescripcion());
        tarea.setResponsable(dto.getResponsable());
        tarea.setEstado(estadoInicial);
        tarea.setFechaCreacion(LocalDateTime.now());

        return tareaRepository.save(tarea);
    }

    public Tarea actualizar(Long id, TareaDTO dto) {
        Tarea tarea = buscarPorId(id);

        if (dto.getEstado() != null && !dto.getEstado().trim().isEmpty()) {
            validarEstado(dto.getEstado());
            tarea.setEstado(dto.getEstado().toUpperCase());
        }

        tarea.setIdCampana(dto.getIdCampana());
        tarea.setDescripcion(dto.getDescripcion());
        tarea.setResponsable(dto.getResponsable());

        return tareaRepository.save(tarea);
    }

    public Tarea actualizarEstado(Long id, String nuevoEstado) {
        if (nuevoEstado == null || nuevoEstado.trim().isEmpty()) {
            throw new IllegalArgumentException("El estado no puede estar vacío");
        }

        validarEstado(nuevoEstado);

        Tarea tarea = buscarPorId(id);
        tarea.setEstado(nuevoEstado.trim().toUpperCase());
        return tareaRepository.save(tarea);
    }

    public void eliminar(Long id) {
        Tarea tarea = buscarPorId(id);
        tareaRepository.delete(tarea);
    }

    private void validarEstado(String estado) {
        if (estado == null || !ESTADOS_VALIDOS.contains(estado.trim().toUpperCase())) {
            throw new IllegalArgumentException("Estado no válido: '" + estado + 
                    "'. Los estados permitidos son: " + ESTADOS_VALIDOS);
        }
    }
}
