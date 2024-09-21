package com.diavlo.bitacora.projects.Infraestructure.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diavlo.bitacora.projects.Applicaction.ProjectService;
import com.diavlo.bitacora.projects.domain.entity.Project;
import com.diavlo.bitacora.projects.domain.entity.ProjectDTO;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ProjectDTO projectDTO) {
        try {
            Project project = projectService.create(projectDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(project);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping
    public List<Project> findAll() {
        return projectService.findAll();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        Optional<Project> id_Project = projectService.findById(id);

        if (id_Project.isPresent()) {
            projectService.deleteById(id);
            return ResponseEntity.ok("Project deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Project with id " + id + " not found.");
        }
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> putMethodName(@PathVariable Long id, @RequestBody ProjectDTO projectDTO) {
        try {
            Optional<Project> updatedProject = projectService.update(id, projectDTO);
            
            if (updatedProject.isPresent()) {
                return ResponseEntity.ok("Project has been updated successfully.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Project with id " + id + " not found.");
            }
            
        } catch (EntityNotFoundException e) {
            // Devuelve una respuesta 404 si no se encuentra la entidad
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entity not found: " + e.getMessage());
        } catch (Exception e) {
            // Maneja otras posibles excepciones
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }

    }
}
