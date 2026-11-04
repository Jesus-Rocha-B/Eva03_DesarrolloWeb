package com.tecsup.controller;

import com.tecsup.dto.CampaniaDTO;
import com.tecsup.model.Campania;
import com.tecsup.services.CampaniaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/campanias")
public class CampaniaController {

    private final CampaniaService campaniaService;

    public CampaniaController(CampaniaService campaniaService) {
        this.campaniaService = campaniaService;
    }

    @GetMapping
    public ResponseEntity<List<Campania>> listarTodas() {
        return ResponseEntity.ok(campaniaService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Campania> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(campaniaService.buscarPorId(id));
    }

    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<List<Campania>> listarPorCliente(@PathVariable Long idCliente) {
        return ResponseEntity.ok(campaniaService.listarPorCliente(idCliente));
    }

    @PostMapping
    public ResponseEntity<Campania> crear(@Valid @RequestBody CampaniaDTO dto) {
        return new ResponseEntity<>(campaniaService.crear(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Campania> actualizar(@PathVariable Long id, @Valid @RequestBody CampaniaDTO dto) {
        return ResponseEntity.ok(campaniaService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        campaniaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
