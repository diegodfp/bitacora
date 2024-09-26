package com.diavlo.bitacora.activitytypes.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActivityTypeDTO {
    private Long activityTypeId;
    private String typeName;
    private String description;
}