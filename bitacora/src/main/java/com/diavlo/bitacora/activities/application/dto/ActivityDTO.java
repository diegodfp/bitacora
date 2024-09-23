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
    private String projectName; // Puedes incluir el nombre del proyecto si lo necesitas
    private Long activityTypeId;
    private String activityTypeName; // Aquí guardamos el nombre del tipo de actividad
    private Long activityStatusId;
    private String activityStatusName; // Aquí guardamos el nombre del estado de la actividad
    private Long priorityId;
    private String priorityLevel; // Aquí guardamos el nivel de prioridad
    private Long createdByUserId;
    private String createdByUserName; // Aquí guardamos el nombre del usuario creador
    private String activityName;
    private String description;
}


