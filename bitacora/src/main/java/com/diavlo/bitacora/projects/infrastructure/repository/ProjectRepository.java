package com.diavlo.bitacora.projects.infrastructure.repository;

import com.diavlo.bitacora.projects.domain.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    
    // Método para listar proyectos por departamento
    @Query("SELECT p FROM Project p WHERE p.department.departmentId = :departmentId")
    List<Project> findByDepartmentId(@Param("departmentId") Long departmentId);
}
