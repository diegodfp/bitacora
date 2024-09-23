package com.diavlo.bitacora.activities.infrastructure.repository;

import com.diavlo.bitacora.activities.domain.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
}
