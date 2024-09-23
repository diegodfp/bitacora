package com.diavlo.bitacora.timelogs.infrastructure.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.diavlo.bitacora.timelogs.domain.entity.Timelog;

@Repository
public interface TimelogRepository extends JpaRepository<Timelog, Long> {

    @Query("SELECT t FROM Timelog t WHERE t.activity.activityId = :activityId AND t.user.userId = :userId AND t.project.projectId = :projectId AND t.endTime IS NULL")
    Optional<Timelog> findOngoingActivity(Long activityId, Long userId, Long projectId);
}
