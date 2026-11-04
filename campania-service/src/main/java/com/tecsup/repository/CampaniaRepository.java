package com.tecsup.repository;

import com.tecsup.model.Campania;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampaniaRepository extends JpaRepository<Campania, Long> {

    List<Campania> findByIdCliente(Long idCliente);
}
