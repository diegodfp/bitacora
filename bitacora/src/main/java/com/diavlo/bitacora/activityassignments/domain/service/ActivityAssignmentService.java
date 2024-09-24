package com.diavlo.bitacora.activityassignments.domain.service;

import com.diavlo.bitacora.activityassignments.domain.entity.ActivityAssignment;
import com.diavlo.bitacora.activityassignments.infrastructure.repository.ActivityAssignmentRepository;
import com.diavlo.bitacora.activities.domain.entity.Activity;
import com.diavlo.bitacora.users.domain.entity.User;
import com.diavlo.bitacora.users.infrastructure.repositories.UserRepository;
import com.diavlo.bitacora.activities.infrastructure.repository.ActivityRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.sql.Timestamp;
import java.util.Optional;

@Service
public class ActivityAssignmentService {

    @Autowired
    private ActivityAssignmentRepository assignmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ActivityRepository activityRepository;

    public ActivityAssignment assignActivity(Long userId, Long activityId, String role) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
        Activity activity = activityRepository.findById(activityId)
            .orElseThrow(() -> new IllegalArgumentException("Actividad no encontrada"));

        // Usando la consulta personalizada con @Query
        Optional<ActivityAssignment> existingAssignment = assignmentRepository.findByUserIdAndActivityId(userId, activityId);
        if (existingAssignment.isPresent()) {
            throw new IllegalArgumentException("El usuario ya tiene asignada esta actividad.");
        }

        ActivityAssignment assignment = ActivityAssignment.builder()
            .user(user)
            .activity(activity)
            .role(role)
            .assignedAt(new Timestamp(System.currentTimeMillis()))
            .build();

        return assignmentRepository.save(assignment);
    }
}
