package com.diavlo.bitacora.activityassignments.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diavlo.bitacora.activityassignments.domain.entity.ActivityAssignment;
import com.diavlo.bitacora.users.domain.entity.User;


import java.util.List;



public interface ActivityAssignmentRepository extends JpaRepository<ActivityAssignment, Long> {
    List<ActivityAssignment> findByUser(User user);
}