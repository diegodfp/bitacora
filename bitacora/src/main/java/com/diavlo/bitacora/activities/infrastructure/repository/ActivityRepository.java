package com.diavlo.bitacora.activities.infrastructure.repository;

import com.diavlo.bitacora.activities.domain.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, Long> {

    @Query("SELECT a FROM Activity a WHERE a.activityId NOT IN " +
            "(SELECT aa.activity.activityId FROM ActivityAssignment aa WHERE aa.user.userId = :userId)")
    List<Activity> findAvailableActivitiesForUser(@Param("userId") Long userId);
}
