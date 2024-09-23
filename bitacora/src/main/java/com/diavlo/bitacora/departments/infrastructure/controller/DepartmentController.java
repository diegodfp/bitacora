package com.diavlo.bitacora.departments.infrastructure.controller;

import com.diavlo.bitacora.departments.application.dto.DepartmentDTO;
import com.diavlo.bitacora.departments.domain.services.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    // Obtener todos los departamentos
    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> getAll() {
        List<DepartmentDTO> departments = departmentService.findAll();
        return ResponseEntity.ok(departments);
    }

    // Obtener un departamento por ID
    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDTO> getById(@PathVariable Long id) {
        Optional<DepartmentDTO> department = departmentService.findById(id);
        return department.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear un nuevo departamento
    @PostMapping
    public ResponseEntity<DepartmentDTO> create(@RequestBody DepartmentDTO departmentDTO) {
        DepartmentDTO createdDepartment = departmentService.create(departmentDTO);
        return ResponseEntity.ok(createdDepartment);
    }

    // Actualizar un departamento existente
    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody DepartmentDTO departmentDTO) {
        Optional<DepartmentDTO> updatedDepartment = departmentService.update(id, departmentDTO);

        if (updatedDepartment.isPresent()) {
            return ResponseEntity.ok("El departamento ha sido actualizado correctamente.");
        } else {
            return ResponseEntity.status(404).body("El departamento con el ID " + id + " no existe.");
        }
    }

    // Eliminar un departamento
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        boolean deleted = departmentService.delete(id);
        if (deleted) {
            return ResponseEntity.ok("El departamento ha sido eliminado correctamente.");
        } else {
            return ResponseEntity.status(404).body("El departamento con el ID " + id + " no existe.");
        }
    }

    // JSON Example for Insomnia:

    /*
    POST /departments
    {
        "departmentName": "IT Department",
        "description": "Handles all IT-related activities"
    }

    PUT /departments/{id}
    {
        "departmentName": "Updated Department Name",
        "description": "Updated description"
    }
    */
}
