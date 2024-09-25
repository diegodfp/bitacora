package com.diavlo.bitacora.priorities.infrastructure.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diavlo.bitacora.priorities.application.PriorityService;
import com.diavlo.bitacora.priorities.domain.entity.Priority;

@RequestMapping("/Priority")
@RestController
public class PriorityController {
    @Autowired
    private PriorityService priorityService;

    @GetMapping("/findAll")
    public List<Priority> findALl(){
        return priorityService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Optional<Priority> find_id = priorityService.findById(id);
          if (find_id.isPresent()) {
        return ResponseEntity.ok(find_id.get());
    } else {
        // Si no se encuentra, devuelve un código 404 Not Found con un mensaje
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                             .body("No se encontró la prioridad con ID: " + id);
    }
    }
}
