package com.diavlo.bitacora.priorities.infrastructure.repository;

import com.diavlo.bitacora.priorities.domain.entity.Priority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriorityRepository extends JpaRepository<Priority, Long> {
}
