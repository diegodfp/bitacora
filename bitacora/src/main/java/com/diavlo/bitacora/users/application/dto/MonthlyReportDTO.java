package com.diavlo.bitacora.users.application.dto;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class MonthlyReportDTO {
    // Mapa que contiene los reportes por proyecto
    private Map<String, ProjectReportDTO> projectReports = new HashMap<>();
}
