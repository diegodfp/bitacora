package com.diavlo.bitacora.projects.application.mappers;

import com.diavlo.bitacora.departments.domain.entity.Department;
import com.diavlo.bitacora.projects.application.dto.ProjectDTO;
import com.diavlo.bitacora.projects.domain.entity.Project;

public class ProjectMapper {

    public static Project toEntity(ProjectDTO dto) {
        return Project.builder()
            .projectId(dto.getProjectId())
            .projectName(dto.getProjectName())
            .description(dto.getDescription())
            .startDate(dto.getStartDate())
            .endDate(dto.getEndDate())
            .department(new Department(dto.getDepartmentId(), null, null, null))  // Solo pasamos el ID del departamento
            .build();
    }

    public static ProjectDTO toDto(Project project) {
        return ProjectDTO.builder()
            .projectId(project.getProjectId())
            .projectName(project.getProjectName())
            .description(project.getDescription())
            .startDate(project.getStartDate())
            .endDate(project.getEndDate())
            .departmentId(project.getDepartment().getDepartmentId())
            .build();
    }
}
