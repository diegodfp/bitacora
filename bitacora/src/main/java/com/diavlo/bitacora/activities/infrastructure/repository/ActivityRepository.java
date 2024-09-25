package com.diavlo.bitacora.activities.infrastructure.repository;

import com.diavlo.bitacora.activities.domain.entity.Activity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
    // Consulta JPQL para obtener actividades asociadas a una prioridad
    @Query("SELECT a FROM Activity a WHERE a.priority.priorityId = :priorityId")
    List<Activity> findAllByPriorityId(@Param("priorityId") Long priorityId);
}
