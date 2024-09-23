package com.diavlo.bitacora.projects.infrastructure.repository;

import com.diavlo.bitacora.projects.domain.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}