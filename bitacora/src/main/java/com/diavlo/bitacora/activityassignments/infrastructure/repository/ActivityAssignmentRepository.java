package com.diavlo.bitacora.activityassignments.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.diavlo.bitacora.activityassignments.domain.entity.ActivityAssignment;
import com.diavlo.bitacora.users.domain.entity.User;


import java.util.List;
import java.util.Optional;


public interface ActivityAssignmentRepository extends JpaRepository<ActivityAssignment, Long> {
    List<ActivityAssignment> findByUser(User user);
     // Consulta JPQL que especifica los campos userId y activityId de las relaciones
    @Query("SELECT aa FROM ActivityAssignment aa WHERE aa.user.userId = :userId AND aa.activity.activityId = :activityId")
    Optional<ActivityAssignment> findByUserIdAndActivityId(@Param("userId") Long userId, @Param("activityId") Long activityId);

}