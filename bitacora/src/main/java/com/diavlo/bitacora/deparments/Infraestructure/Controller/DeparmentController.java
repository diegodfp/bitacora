package com.diavlo.bitacora.deparments.Infraestructure.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.diavlo.bitacora.deparments.Application.DeparmentService;
import com.diavlo.bitacora.deparments.domain.entity.Department;


@RequestMapping("/Department")
@RestController
public class DeparmentController {
    @Autowired
    private DeparmentService deparmentService;

    @GetMapping
    public List<Department> findAll() {
        return deparmentService.findAll();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        Optional<Department> id_Department = deparmentService.findByid(id);
        if (id_Department.isPresent()) {
            deparmentService.deleteById(id);
            return ResponseEntity.ok("Department deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Department with id " + id + " not found.");
        }
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> updateById(@PathVariable Long id, @RequestBody Department department) {
        Optional<Department> updatedDepartment = deparmentService.update(id, department);

        if (updatedDepartment.isPresent()) {
            return ResponseEntity.ok(updatedDepartment.get()); // Devuelve el departamento actualizado
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Department with id " + id + " not found.");
        }
    }
    @PostMapping
    public ResponseEntity<?> createDepartment(@RequestBody Department department){
        Optional<Department> create = deparmentService.create(department);
        
        if (create.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(create.get()); //  departamento creado
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create department."); // Maneja el error de creación
        }
         
    }
}
