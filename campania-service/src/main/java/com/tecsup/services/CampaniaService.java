package com.tecsup.services;

import com.tecsup.dto.CampaniaDTO;
import com.tecsup.exception.ResourceNotFoundException;
import com.tecsup.model.Campania;
import com.tecsup.repository.CampaniaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampaniaService {

    private final CampaniaRepository campaniaRepository;

    public CampaniaService(CampaniaRepository campaniaRepository) {
        this.campaniaRepository = campaniaRepository;
    }

    public List<Campania> listarTodas() {
        return campaniaRepository.findAll();
    }

    public Campania buscarPorId(Long id) {
        return campaniaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Campaña no encontrada con ID: " + id));
    }

    public List<Campania> listarPorCliente(Long idCliente) {
        return campaniaRepository.findByIdCliente(idCliente);
    }

    public Campania crear(CampaniaDTO dto) {
        validarReglasDeNegocio(dto);

        Campania campania = new Campania();
        campania.setNombre(dto.getNombre());
        campania.setFechaInicio(dto.getFechaInicio());
        campania.setFechaFin(dto.getFechaFin());
        campania.setPresupuesto(dto.getPresupuesto());
        campania.setIdCliente(dto.getIdCliente());
        campania.setEstado(dto.getEstado() != null ? dto.getEstado() : "PLANIFICADA");

        return campaniaRepository.save(campania);
    }

    public Campania actualizar(Long id, CampaniaDTO dto) {
        validarReglasDeNegocio(dto);

        Campania campania = buscarPorId(id);
        campania.setNombre(dto.getNombre());
        campania.setFechaInicio(dto.getFechaInicio());
        campania.setFechaFin(dto.getFechaFin());
        campania.setPresupuesto(dto.getPresupuesto());
        campania.setIdCliente(dto.getIdCliente());
        if (dto.getEstado() != null) {
            campania.setEstado(dto.getEstado());
        }

        return campaniaRepository.save(campania);
    }

    public void eliminar(Long id) {
        Campania campania = buscarPorId(id);
        campaniaRepository.delete(campania);
    }

    private void validarReglasDeNegocio(CampaniaDTO dto) {
        if (dto.getFechaFin() != null && dto.getFechaInicio() != null) {
            if (dto.getFechaFin().isBefore(dto.getFechaInicio())) {
                throw new IllegalArgumentException("La fecha de fin (" + dto.getFechaFin() + 
                        ") no puede ser anterior a la fecha de inicio (" + dto.getFechaInicio() + ")");
            }
        }
        if (dto.getPresupuesto() != null && dto.getPresupuesto() < 0) {
            throw new IllegalArgumentException("El presupuesto no puede ser negativo: " + dto.getPresupuesto());
        }
    }
}
