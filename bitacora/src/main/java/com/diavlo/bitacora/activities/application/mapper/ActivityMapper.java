package com.diavlo.bitacora.activities.application.mapper;

import com.diavlo.bitacora.activities.application.dto.ActivityDTO;
import com.diavlo.bitacora.activities.domain.entity.Activity;
import com.diavlo.bitacora.projects.domain.entity.Project;
import com.diavlo.bitacora.activitytypes.domain.entity.ActivityType;
import com.diavlo.bitacora.activitystatuses.domain.entity.ActivityStatus;
import com.diavlo.bitacora.priorities.domain.entity.Priority;
import com.diavlo.bitacora.users.domain.entity.User;

public class ActivityMapper {

    public static ActivityDTO toDTO(Activity activity) {
        return ActivityDTO.builder()
                .activityId(activity.getActivityId())
                .projectId(activity.getProject().getProjectId())
                .projectName(activity.getProject().getProjectName()) 
                .activityTypeId(activity.getActivityType().getActivityTypeId())
                .activityTypeName(activity.getActivityType().getTypeName()) 
                .activityStatusId(activity.getActivityStatus().getStatusId())
                .activityStatusName(activity.getActivityStatus().getStatusName()) 
                .priorityId(activity.getPriority().getPriorityId())
                .priorityLevel(activity.getPriority().getPriorityLevel()) 
                .createdByUserId(activity.getCreatedByUser().getUserId())
                .createdByUserName(activity.getCreatedByUser().getFullName())
                .activityName(activity.getActivityName())
                .description(activity.getDescription())
                .build();
    }

    public static Activity toEntity(ActivityDTO activityDTO, Project project, ActivityType activityType,
                                    ActivityStatus activityStatus, Priority priority, User createdByUser) {
        return Activity.builder()
                .activityId(activityDTO.getActivityId())
                .project(project)
                .activityType(activityType)
                .activityStatus(activityStatus)
                .priority(priority)
                .createdByUser(createdByUser)
                .activityName(activityDTO.getActivityName())
                .description(activityDTO.getDescription())
                .build();
    }
}

