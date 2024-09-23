package com.diavlo.bitacora.activities.domain.services;

import com.diavlo.bitacora.activities.application.dto.ActivityDTO;
import com.diavlo.bitacora.activities.application.mapper.ActivityMapper;
import com.diavlo.bitacora.activities.domain.entity.Activity;
import com.diavlo.bitacora.activities.infrastructure.repository.ActivityRepository;
import com.diavlo.bitacora.activityassignments.domain.entity.ActivityAssignment;
import com.diavlo.bitacora.activityassignments.infrastructure.repository.ActivityAssignmentRepository;
import com.diavlo.bitacora.projects.domain.entity.Project;
import com.diavlo.bitacora.activitytypes.domain.entity.ActivityType;
import com.diavlo.bitacora.activitystatuses.domain.entity.ActivityStatus;
import com.diavlo.bitacora.priorities.domain.entity.Priority;
import com.diavlo.bitacora.users.domain.entity.User;
import com.diavlo.bitacora.projects.infrastructure.repository.ProjectRepository;
import com.diavlo.bitacora.activitytypes.infrastructure.repository.ActivityTypeRepository;
import com.diavlo.bitacora.activitystatuses.infrastructure.repository.ActivityStatusRepository;
import com.diavlo.bitacora.priorities.infrastructure.repository.PriorityRepository;
import com.diavlo.bitacora.users.infrastructure.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ActivityService {

    private final ActivityRepository activityRepository;
    private final ProjectRepository projectRepository;
    private final ActivityTypeRepository activityTypeRepository;
    private final ActivityStatusRepository activityStatusRepository;
    private final PriorityRepository priorityRepository;
    private final UserRepository userRepository;
    private final ActivityAssignmentRepository activityAssignmentRepository;

    @Transactional
    public ActivityDTO createActivity(ActivityDTO activityDTO) {
        Project project = projectRepository.findById(activityDTO.getProjectId())
                .orElseThrow(() -> new IllegalArgumentException("Project not found"));
        ActivityType activityType = activityTypeRepository.findById(activityDTO.getActivityTypeId())
                .orElseThrow(() -> new IllegalArgumentException("Activity Type not found"));
        ActivityStatus activityStatus = activityStatusRepository.findById(activityDTO.getActivityStatusId())
                .orElseThrow(() -> new IllegalArgumentException("Activity Status not found"));
        Priority priority = priorityRepository.findById(activityDTO.getPriorityId())
                .orElseThrow(() -> new IllegalArgumentException("Priority not found"));
        User createdByUser = userRepository.findById(activityDTO.getCreatedByUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Activity activity = ActivityMapper.toEntity(activityDTO, project, activityType, activityStatus, priority,
                createdByUser);

        Activity savedActivity = activityRepository.save(activity);

        // Asignamos los nombres al DTO
        ActivityDTO responseDTO = ActivityMapper.toDTO(savedActivity);
        responseDTO.setActivityTypeName(activityType.getTypeName());
        responseDTO.setActivityStatusName(activityStatus.getStatusName());
        responseDTO.setPriorityLevel(priority.getPriorityLevel());
        responseDTO.setCreatedByUserName(createdByUser.getFullName());

        return responseDTO;
    }

    @Transactional
    public Optional<ActivityDTO> updateActivity(Long id, ActivityDTO activityDTO) {
        Optional<Activity> optionalActivity = activityRepository.findById(id);
        if (optionalActivity.isPresent()) {
            Activity existingActivity = optionalActivity.get();

            Project project = projectRepository.findById(activityDTO.getProjectId())
                    .orElseThrow(() -> new IllegalArgumentException("Project not found"));
            ActivityType activityType = activityTypeRepository.findById(activityDTO.getActivityTypeId())
                    .orElseThrow(() -> new IllegalArgumentException("Activity Type not found"));
            ActivityStatus activityStatus = activityStatusRepository.findById(activityDTO.getActivityStatusId())
                    .orElseThrow(() -> new IllegalArgumentException("Activity Status not found"));
            Priority priority = priorityRepository.findById(activityDTO.getPriorityId())
                    .orElseThrow(() -> new IllegalArgumentException("Priority not found"));
            User createdByUser = userRepository.findById(activityDTO.getCreatedByUserId())
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));

            Activity updatedActivity = ActivityMapper.toEntity(activityDTO, project, activityType, activityStatus,
                    priority, createdByUser);
            updatedActivity.setActivityId(id); // Forzamos la actualización con el ID existente

            return Optional.of(ActivityMapper.toDTO(activityRepository.save(updatedActivity)));
        }
        return Optional.empty();
    }

    public Optional<ActivityDTO> getActivityById(Long id) {
        return activityRepository.findById(id).map(ActivityMapper::toDTO);
    }

    public List<ActivityDTO> getAllActivities() {
        return activityRepository.findAll().stream().map(ActivityMapper::toDTO).collect(Collectors.toList());
    }

    @Transactional
    public boolean deleteActivity(Long id) {
        if (activityRepository.existsById(id)) {
            activityRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<ActivityDTO> getUserActivities(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        List<ActivityAssignment> assignments = activityAssignmentRepository.findByUser(user);
        return assignments.stream()
                .map(activityAssignment -> ActivityMapper.toDTO(activityAssignment.getActivity()))
                .collect(Collectors.toList());
    }

}
