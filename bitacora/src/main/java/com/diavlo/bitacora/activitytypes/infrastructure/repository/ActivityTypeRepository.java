package com.diavlo.bitacora.activitytypes.infrastructure.repository;

import com.diavlo.bitacora.activitytypes.domain.entity.ActivityType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityTypeRepository extends JpaRepository<ActivityType, Long> {
}
