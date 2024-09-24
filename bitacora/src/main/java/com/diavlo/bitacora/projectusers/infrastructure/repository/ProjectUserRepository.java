package com.diavlo.bitacora.projectusers.infrastructure.repository;

import com.diavlo.bitacora.projectusers.domain.entity.ProjectUser;
import com.diavlo.bitacora.projects.domain.entity.Project;
import com.diavlo.bitacora.users.domain.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectUserRepository extends CrudRepository<ProjectUser, Long> {

    @Query("SELECT CASE WHEN COUNT(pu) > 0 THEN true ELSE false END FROM ProjectUser pu WHERE pu.user = :user AND pu.project = :project")
    Boolean existsByUserAndProject(User user, Project project);
}
