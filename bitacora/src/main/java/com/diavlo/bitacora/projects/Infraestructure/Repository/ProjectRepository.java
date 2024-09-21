package com.diavlo.bitacora.projects.Infraestructure.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diavlo.bitacora.projects.domain.entity.Project;

public interface ProjectRepository extends JpaRepository<Project,Long>{

}
