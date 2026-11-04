package com.tecsup.controller;

import com.tecsup.dto.ActualizarEstadoDTO;
import com.tecsup.dto.TareaDTO;
import com.tecsup.model.Tarea;
import com.tecsup.services.TareaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tareas")
public class TareaController {

    private final TareaService tareaService;

    public TareaController(TareaService tareaService) {
        this.tareaService = tareaService;
    }

    @GetMapping
    public ResponseEntity<List<Tarea>> listarTodas() {
        return ResponseEntity.ok(tareaService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarea> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(tareaService.buscarPorId(id));
    }

    @GetMapping("/campana/{idCampana}")
    public ResponseEntity<List<Tarea>> listarPorCampana(@PathVariable Long idCampana) {
        return ResponseEntity.ok(tareaService.listarPorCampana(idCampana));
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Tarea>> listarPorEstado(@PathVariable String estado) {
        return ResponseEntity.ok(tareaService.listarPorEstado(estado));
    }

    @PostMapping
    public ResponseEntity<Tarea> crear(@Valid @RequestBody TareaDTO dto) {
        return new ResponseEntity<>(tareaService.crear(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarea> actualizar(@PathVariable Long id, @Valid @RequestBody TareaDTO dto) {
        return ResponseEntity.ok(tareaService.actualizar(id, dto));
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<Tarea> actualizarEstadoPut(@PathVariable Long id, @Valid @RequestBody ActualizarEstadoDTO dto) {
        return ResponseEntity.ok(tareaService.actualizarEstado(id, dto.getEstado()));
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<Tarea> actualizarEstadoPatch(@PathVariable Long id, @Valid @RequestBody ActualizarEstadoDTO dto) {
        return ResponseEntity.ok(tareaService.actualizarEstado(id, dto.getEstado()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        tareaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
