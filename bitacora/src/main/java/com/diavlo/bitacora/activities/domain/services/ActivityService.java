package com.diavlo.bitacora.activities.domain.services;

import com.diavlo.bitacora.activities.application.dto.ActivityChangeStatusDTO;
import com.diavlo.bitacora.activities.application.dto.ActivityDTO;
import com.diavlo.bitacora.activities.application.mapper.ActivityChangeStatusMapper;
import com.diavlo.bitacora.activities.application.mapper.ActivityMapper;
import com.diavlo.bitacora.activities.domain.entity.Activity;
import com.diavlo.bitacora.activities.infrastructure.repository.ActivityRepository;
import com.diavlo.bitacora.activityassignments.domain.entity.ActivityAssignment;
import com.diavlo.bitacora.activityassignments.infrastructure.repository.ActivityAssignmentRepository;
import com.diavlo.bitacora.activitychangestatus.domain.entity.ActivityStatusChange;
import com.diavlo.bitacora.activitychangestatus.infrastructure.repository.ActivityChangeStatusRepository;
import com.diavlo.bitacora.projects.domain.entity.Project;
import com.diavlo.bitacora.activitytypes.domain.entity.ActivityType;
import com.diavlo.bitacora.activitystatuses.domain.entity.ActivityStatus;
import com.diavlo.bitacora.priorities.domain.entity.Priority;
import com.diavlo.bitacora.users.domain.entity.User;
import com.diavlo.bitacora.projects.infrastructure.repository.ProjectRepository;
import com.diavlo.bitacora.activitytypes.infrastructure.repository.ActivityTypeRepository;
import com.diavlo.bitacora.common.domain.entities.TimeCreateUpdate;
import com.diavlo.bitacora.activitystatuses.infrastructure.repository.ActivityStatusRepository;
import com.diavlo.bitacora.priorities.infrastructure.repository.PriorityRepository;
import com.diavlo.bitacora.users.infrastructure.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ActivityService {

        @Autowired
        private ActivityRepository activityRepository;
        @Autowired
        private ProjectRepository projectRepository;
        @Autowired
        private ActivityTypeRepository activityTypeRepository;
        @Autowired
        private ActivityStatusRepository activityStatusRepository;
        @Autowired
        private PriorityRepository priorityRepository;
        @Autowired
        private UserRepository userRepository;
        @Autowired
        private ActivityAssignmentRepository activityAssignmentRepository;
        @Autowired
        private ActivityChangeStatusRepository activityChangeStatusRepository;

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

                Activity activity = ActivityMapper.toEntity(activityDTO, project, activityType, activityStatus,
                                priority, createdByUser);

                // Aseguramos que la instancia de TimeCreateUpdate no sea nula
                if (activity.getTimeCreateUpdate() == null) {
                        activity.setTimeCreateUpdate(new TimeCreateUpdate());
                }

                Activity savedActivity = activityRepository.save(activity);

                ActivityDTO responseDTO = ActivityMapper.toDTO(savedActivity);
                responseDTO.setActivityTypeName(activityType.getTypeName());
                responseDTO.setActivityStatusName(activityStatus.getStatusName());
                responseDTO.setPriorityLevel(priority.getPriorityLevel());
                responseDTO.setCreatedByUserName(createdByUser.getFullName());

                return responseDTO;
        }

        // Método para actualizar el estado de una actividad
        @Transactional
        public Optional<ActivityDTO> updateActivityStatus(Long id, ActivityChangeStatusDTO activityChangeStatusDTO) {
                Optional<Activity> optionalActivity = activityRepository.findById(id);
                if (optionalActivity.isPresent()) {
                        Activity activity = optionalActivity.get();

                        ActivityStatus activityStatus = activityStatusRepository
                                        .findById(activityChangeStatusDTO.getStatus_id())
                                        .orElseThrow(() -> new IllegalArgumentException("Activity Status not found"));
                        User changedByUser = userRepository.findById(activityChangeStatusDTO.getChanged_by_user_id())
                                        .orElseThrow(() -> new IllegalArgumentException("User not found"));

                        // Crear el cambio de estado
                        ActivityStatusChange changeStatus = new ActivityStatusChange();
                        changeStatus.setActivity(activity);
                        changeStatus.setChangedByUser(changedByUser);
                        changeStatus.setActivityStatus(activityStatus);
                        changeStatus.setChangeComment(activityChangeStatusDTO.getChange_comment());

                        // Asignar la fecha de creación
                        TimeCreateUpdate timeCreateUpdate = new TimeCreateUpdate();
                        timeCreateUpdate.setCreatedAt(LocalDateTime.now());
                        changeStatus.setTimeCreateUpdate(timeCreateUpdate);

                        // Guardar el cambio de estado
                        activityChangeStatusRepository.save(changeStatus);

                        // Actualizar el estado actual de la actividad
                        activity.setActivityStatus(activityStatus);
                        activityRepository.save(activity);

                        return Optional.of(ActivityMapper.toDTO(activity));
                }
                return Optional.empty();
        }

        // Método para actualizar los demás campos de una actividad
    @Transactional
    public Optional<ActivityDTO> updateActivityDetails(Long id, ActivityDTO activityDTO) {
        Optional<Activity> optionalActivity = activityRepository.findById(id);
        if (optionalActivity.isPresent()) {
            Activity activity = optionalActivity.get();

            Project project = projectRepository.findById(activityDTO.getProjectId())
                .orElseThrow(() -> new IllegalArgumentException("Project not found"));
            ActivityType activityType = activityTypeRepository.findById(activityDTO.getActivityTypeId())
                .orElseThrow(() -> new IllegalArgumentException("Activity Type not found"));
            Priority priority = priorityRepository.findById(activityDTO.getPriorityId())
                .orElseThrow(() -> new IllegalArgumentException("Priority not found"));

            // Actualizar solo los campos relevantes (nombre, descripción, prioridad, etc.)
            activity.setProject(project);
            activity.setActivityType(activityType);
            activity.setPriority(priority);
            activity.setActivityName(activityDTO.getActivityName());
            activity.setDescription(activityDTO.getDescription());
             // Aseguramos que la instancia de TimeCreateUpdate no sea nula
        if (activity.getTimeCreateUpdate() == null) {
                activity.setTimeCreateUpdate(new TimeCreateUpdate());
            }
    
            // No cambiar createdAt, solo actualizar updatedAt
            activity.getTimeCreateUpdate().setUpdatedAt(LocalDateTime.now());

            activityRepository.save(activity);
            return Optional.of(ActivityMapper.toDTO(activity));
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
