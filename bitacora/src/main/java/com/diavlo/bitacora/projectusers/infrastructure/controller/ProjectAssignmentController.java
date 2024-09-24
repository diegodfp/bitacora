package com.diavlo.bitacora.projectusers.infrastructure.controller;

import com.diavlo.bitacora.projectusers.application.dto.ProjectAssignmentDTO;
import com.diavlo.bitacora.projectusers.domain.entity.ProjectUser;
import com.diavlo.bitacora.projectusers.domain.service.ProjectUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/project-assignments")
public class ProjectAssignmentController {

    @Autowired
    private ProjectUserService projectUserService;

    @PostMapping("/assign")
    public ResponseEntity<ProjectUser> assignProject(@RequestBody ProjectAssignmentDTO request) {
        ProjectUser projectUser = projectUserService.assignProjectToUser(request);
        return ResponseEntity.ok(projectUser);
    }
}
