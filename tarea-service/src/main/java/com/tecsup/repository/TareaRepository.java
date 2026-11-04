package com.tecsup.repository;

import com.tecsup.model.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TareaRepository extends JpaRepository<Tarea, Long> {

    List<Tarea> findByIdCampana(Long idCampana);

    List<Tarea> findByEstado(String estado);
}
