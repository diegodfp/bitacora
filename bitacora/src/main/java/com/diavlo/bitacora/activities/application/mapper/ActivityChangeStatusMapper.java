package com.diavlo.bitacora.activities.application.mapper;

import com.diavlo.bitacora.activities.application.dto.ActivityChangeStatusDTO;
import com.diavlo.bitacora.activities.domain.entity.Activity;
import com.diavlo.bitacora.activitychangestatus.domain.entity.ActivityStatusChange;
import com.diavlo.bitacora.activitystatuses.domain.entity.ActivityStatus;
import com.diavlo.bitacora.users.domain.entity.User;

public class ActivityChangeStatusMapper {
    public static ActivityChangeStatusDTO toDTO(ActivityStatusChange activityStatusChange) {
        return ActivityChangeStatusDTO.builder()
                .activity_id(activityStatusChange.getActivityStatusChangeId()) // Cambiado a camelCase
                .changed_by_user_id(activityStatusChange.getChangedByUser().getUserId()) // Cambiado a camelCase
                .status_id(activityStatusChange.getActivityStatus().getStatusId()) // Cambiado a camelCase
                .change_comment(activityStatusChange.getChangeComment()) // Cambiado a camelCase
                .build();
    }

    public static ActivityStatusChange toEntity(ActivityChangeStatusDTO activityChangeStatusDTO,
            Activity activity, User user, ActivityStatus activityStatus) {
        return ActivityStatusChange.builder()
                .activityStatusChangeId(activityChangeStatusDTO.getActivity_id()) // Cambiado a camelCase
                .activity(activity)
                .activityStatus(activityStatus)
                .changedByUser(user)
                .changeComment(activityChangeStatusDTO.getChange_comment()) // Cambiado a camelCase
                .build();
    }
}
