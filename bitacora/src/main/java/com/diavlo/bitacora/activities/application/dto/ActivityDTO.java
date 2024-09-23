package com.diavlo.bitacora.activities.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActivityDTO {
    private Long activityId;
    private Long projectId;
    private Long activityTypeId;
    private Long activityStatusId;
    private Long priorityId;
    private Long createdByUserId;
    private String activityName;
    private String description;
}

