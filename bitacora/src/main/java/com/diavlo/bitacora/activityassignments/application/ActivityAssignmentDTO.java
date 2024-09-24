package com.diavlo.bitacora.activityassignments.application;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActivityAssignmentDTO {
    private Long userId;
    private Long activityId;
    private String role;
}
