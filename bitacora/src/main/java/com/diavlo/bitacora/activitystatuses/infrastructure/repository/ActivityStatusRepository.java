package com.diavlo.bitacora.activitystatuses.infrastructure.repository;

import com.diavlo.bitacora.activitystatuses.domain.entity.ActivityStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityStatusRepository extends JpaRepository<ActivityStatus, Long> {
}
