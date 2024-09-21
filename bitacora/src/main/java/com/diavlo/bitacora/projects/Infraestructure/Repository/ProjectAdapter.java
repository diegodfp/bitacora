package com.diavlo.bitacora.projects.Infraestructure.Repository;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diavlo.bitacora.deparments.Infraestructure.Repository.DeparmentsRepository;
import com.diavlo.bitacora.deparments.domain.entity.Department;
import com.diavlo.bitacora.projects.Applicaction.ProjectService;
import com.diavlo.bitacora.projects.domain.entity.Project;
import com.diavlo.bitacora.projects.domain.entity.ProjectDTO;

@Service
public class ProjectAdapter implements ProjectService {
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private DeparmentsRepository deparmentsRepository;

    @Override
    public Project create(ProjectDTO projectDTO) {
        Long department_id = projectDTO.getDepartment_id();
        Optional<Department> departmentFind = deparmentsRepository.findById(department_id);

        Project project = new Project();
        project.setDepartment(departmentFind.get());
        project.setDescription(projectDTO.getDescription());
        project.setEndDate(projectDTO.getEnd_date());
        project.setProjectName(projectDTO.getProject_name());

        return projectRepository.save(project);
    }

    @Override
    public Optional<Project> findById(Long id) {
        return projectRepository.findById(id);
    }

    @Override
    public Optional<Project> update(Long id, ProjectDTO projectDTO) {
        Optional<Project> Id_Project_Exists = projectRepository.findById(id);
        if (Id_Project_Exists.isPresent()) {

            Project project = Id_Project_Exists.get();
            Long department_id = projectDTO.getDepartment_id();
            Optional<Department> departmentFind = deparmentsRepository.findById(department_id);

            if (departmentFind.isPresent()) {

                project.setDepartment(departmentFind.get());
                project.setDescription(projectDTO.getDescription());
                project.setEndDate(projectDTO.getEnd_date());
                project.setProjectName(projectDTO.getProject_name());

                Project project_Updated = projectRepository.save(project);

                return Optional.of(project_Updated);

            }

        }
        // Si no se encuentra el proyecto o el departamento, retornar vacío suave
        return Optional.empty();
    }

    @Override
    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        projectRepository.deleteById(id);
    }

}
