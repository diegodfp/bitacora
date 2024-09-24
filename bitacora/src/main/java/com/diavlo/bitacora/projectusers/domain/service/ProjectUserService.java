package com.diavlo.bitacora.projectusers.domain.service;

import com.diavlo.bitacora.projectusers.domain.entity.ProjectUser;
import com.diavlo.bitacora.projectusers.application.dto.ProjectAssignmentDTO;
import com.diavlo.bitacora.projectusers.infrastructure.repository.ProjectUserRepository;
import com.diavlo.bitacora.projects.domain.entity.Project;
import com.diavlo.bitacora.projects.infrastructure.repository.ProjectRepository;
import com.diavlo.bitacora.users.domain.entity.User;
import com.diavlo.bitacora.users.infrastructure.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ProjectUserService {

    @Autowired
    private ProjectUserRepository projectUserRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public ProjectUser assignProjectToUser(ProjectAssignmentDTO request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        Project project = projectRepository.findById(request.getProjectId())
                .orElseThrow(() -> new IllegalArgumentException("Proyecto no encontrado"));

        // Verificar si ya existe la asignación del proyecto
        boolean exists = projectUserRepository.existsByUserAndProject(user, project);
        if (exists) {
            throw new IllegalArgumentException("El usuario ya está asignado a este proyecto.");
        }

        // Crear la nueva asignación
        ProjectUser projectUser = new ProjectUser();
        projectUser.setUser(user);
        projectUser.setProject(project);
        projectUser.setIsLeader(request.getIsLeader());
        projectUser.setAssignedDate(LocalDateTime.now());

        return projectUserRepository.save(projectUser);
    }
}
