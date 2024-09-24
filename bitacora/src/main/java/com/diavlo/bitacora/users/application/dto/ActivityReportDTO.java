package com.diavlo.bitacora.users.application.dto;

import lombok.Data;

@Data
public class ActivityReportDTO {
    private String activityName;
    private int timeSpent;

    public ActivityReportDTO(String activityName) {
        this.activityName = activityName;
    }

    // Sumar el tiempo invertido en la actividad
    public void addTimeSpent(int timeSpent) {
        this.timeSpent += timeSpent;
    }
}
