package com.diavlo.bitacora.priorities.infrastructure.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diavlo.bitacora.priorities.application.dto.PriorityDTO;
import com.diavlo.bitacora.priorities.domain.service.PriorityService;

import java.util.List;

@RestController
@RequestMapping("/priorities")
public class PriorityController {
private final PriorityService priorityService;

    public PriorityController(PriorityService priorityService) {
        this.priorityService = priorityService;
    }

    @GetMapping
    public ResponseEntity<List<PriorityDTO>> getAllPriorities() {
        List<PriorityDTO> priorities = priorityService.getAllPriorities();
        return ResponseEntity.ok(priorities);
    }
}
