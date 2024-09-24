package com.diavlo.bitacora.projects.domain.services;

import com.diavlo.bitacora.common.domain.entities.TimeCreateUpdate;
import com.diavlo.bitacora.departments.domain.entity.Department;
import com.diavlo.bitacora.projects.application.dto.ProjectDTO;
import com.diavlo.bitacora.projects.application.mappers.ProjectMapper;
import com.diavlo.bitacora.projects.domain.entity.Project;
import com.diavlo.bitacora.projects.infrastructure.repository.ProjectRepository;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Transactional
    public ProjectDTO createProject(ProjectDTO dto) {
    // Crear la entidad desde el DTO
    Project project = ProjectMapper.toEntity(dto);

    // Asegurarse de que el campo timeCreateUpdate esté inicializado
    if (project.getTimeCreateUpdate() == null) {
        project.setTimeCreateUpdate(new TimeCreateUpdate());
    }

    // Guardar el proyecto
    Project savedProject = projectRepository.save(project);

    // Retornar el DTO del proyecto guardado
    return ProjectMapper.toDto(savedProject);
}

    @Transactional
    public Optional<ProjectDTO> updateProject(Long id, ProjectDTO dto) {
        return projectRepository.findById(id).map(project -> {
            project.setProjectName(dto.getProjectName());
            project.setDescription(dto.getDescription());
            project.setStartDate(dto.getStartDate());
            project.setEndDate(dto.getEndDate());
            project.setDepartment(new Department(dto.getDepartmentId(), null, null, null));  // Actualizamos el departamento
            return ProjectMapper.toDto(projectRepository.save(project));
        });
    }

    @Transactional
    public Optional<ProjectDTO> findProjectById(Long id) {
        return projectRepository.findById(id).map(ProjectMapper::toDto);
    }

    @Transactional
    public Page<ProjectDTO> findAllProjects(Pageable pageable) {
        return projectRepository.findAll(pageable).map(ProjectMapper::toDto);
    }

    @Transactional
    public Optional<ProjectDTO> deleteProject(Long id) {
        return projectRepository.findById(id).map(project -> {
            projectRepository.delete(project);
            return ProjectMapper.toDto(project);
        });
    }

     // método para obtener proyectos por ID de departamento
    @Transactional
    public List<ProjectDTO> findProjectsByDepartmentId(Long departmentId) {
        List<Project> projects = projectRepository.findByDepartmentId(departmentId);
        return projects.stream().map(ProjectMapper::toDto).toList();
    }
}
