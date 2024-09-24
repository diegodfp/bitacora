package com.diavlo.bitacora.users.application.dto;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ProjectReportDTO {
    private String projectName;
    private Map<String, ActivityReportDTO> activities = new HashMap<>();
    private int totalTimeSpent;

    public ProjectReportDTO(String projectName) {
        this.projectName = projectName;
    }

    // Sumar el tiempo total invertido en el proyecto
    public void addTotalTime(int timeSpent) {
        this.totalTimeSpent += timeSpent;
    }
}
