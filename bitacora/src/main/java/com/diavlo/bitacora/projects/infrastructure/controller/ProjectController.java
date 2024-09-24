package com.diavlo.bitacora.projects.infrastructure.controller;

import com.diavlo.bitacora.projects.application.dto.ProjectDTO;
import com.diavlo.bitacora.projects.domain.services.ProjectService;
import lombok.RequiredArgsConstructor;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.List;


@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    // Crear un proyecto
    @PostMapping("/create")
    public ResponseEntity<ProjectDTO> createProject(@RequestBody ProjectDTO projectDTO) {
        ProjectDTO createdProject = projectService.createProject(projectDTO);
        return ResponseEntity.ok(createdProject);
    }

    // Actualizar un proyecto
    @PutMapping("/update/{id}")
    public ResponseEntity<Optional<ProjectDTO>> updateProject(@PathVariable Long id, @RequestBody ProjectDTO projectDTO) {
        Optional<ProjectDTO> updatedProject = projectService.updateProject(id, projectDTO);
        return ResponseEntity.ok(updatedProject);
    }

    // Eliminar un proyecto
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Optional<ProjectDTO>> deleteProject(@PathVariable Long id) {
        Optional<ProjectDTO> deletedProject = projectService.deleteProject(id);
        return ResponseEntity.ok(deletedProject);
    }

    // Buscar un proyecto por ID
    @GetMapping("/find/{id}")
    public ResponseEntity<Optional<ProjectDTO>> findProjectById(@PathVariable Long id) {
        Optional<ProjectDTO> project = projectService.findProjectById(id);
        return ResponseEntity.ok(project);
    }

    // Listar todos los proyectos
    @GetMapping("/findAll")
    public ResponseEntity<Page<ProjectDTO>> findAllProjects(Pageable pageable) {
        Page<ProjectDTO> projects = projectService.findAllProjects(pageable);
        return ResponseEntity.ok(projects);
    }

    // Listar proyectos por departamento
    @GetMapping("/department/{departmentId}")
    public ResponseEntity<List<ProjectDTO>> findProjectsByDepartmentId(@PathVariable Long departmentId) {
        List<ProjectDTO> projects = projectService.findProjectsByDepartmentId(departmentId);
        return ResponseEntity.ok(projects);
    }

    // JSON de ejemplo de creacion para Insomnia:
    /*
    {
      "projectName": "Proyecto Creado from endpoint",
      "description": "Este proyecto fue creado desde el endpoint",
      "startDate": "2024-09-25",
      "endDate": "2024-10-25",
      "departmentId": 1
    }
    */
}
