package com.diavlo.bitacora.activities.application.mapper;

import com.diavlo.bitacora.activities.application.dto.ActivityDTO;
import com.diavlo.bitacora.activities.domain.entity.Activity;
import com.diavlo.bitacora.projects.domain.entity.Project;
import com.diavlo.bitacora.activitytypes.domain.entity.ActivityType;
import com.diavlo.bitacora.activitystatuses.domain.entity.ActivityStatus;
import com.diavlo.bitacora.priorities.domain.entity.Priority;
import com.diavlo.bitacora.users.domain.entity.User;

public class ActivityMapper {

    public static Activity toEntity(ActivityDTO activityDTO, Project project, ActivityType activityType, 
                                    ActivityStatus activityStatus, Priority priority, User createdByUser) {
        return Activity.builder()
            .activityName(activityDTO.getActivityName())
            .description(activityDTO.getDescription())
            .project(project)
            .activityType(activityType)
            .activityStatus(activityStatus)
            .priority(priority)
            .createdByUser(createdByUser)
            .build();
    }

    public static ActivityDTO toDTO(Activity activity) {
        return ActivityDTO.builder()
            .activityId(activity.getActivityId())
            .activityName(activity.getActivityName())
            .description(activity.getDescription())
            .projectId(activity.getProject().getProjectId())
            .activityTypeId(activity.getActivityType().getActivityTypeId())
            .activityStatusId(activity.getActivityStatus().getStatusId())
            .priorityId(activity.getPriority().getPriorityId())
            .createdByUserId(activity.getCreatedByUser().getUserId()) // Ajusta según tu entidad User
            .build();
    }
}
