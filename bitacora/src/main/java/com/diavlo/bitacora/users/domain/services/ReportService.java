package com.diavlo.bitacora.users.domain.services;


import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diavlo.bitacora.timelogs.domain.entity.Timelog;
import com.diavlo.bitacora.timelogs.infrastructure.repository.TimelogRepository;
import com.diavlo.bitacora.users.application.dto.ActivityReportDTO;
import com.diavlo.bitacora.users.application.dto.MonthlyReportDTO;
import com.diavlo.bitacora.users.application.dto.ProjectReportDTO;

@Service
public class ReportService {
@Autowired
    private TimelogRepository timelogRepository;

    public MonthlyReportDTO generateMonthlyReport(Long userId, LocalDate startDate, LocalDate endDate) {
        // Recuperamos todos los timelogs del usuario en el rango de fechas indicado
        List<Timelog> timelogs = timelogRepository.findByUserIdAndWorkDateBetween(userId, startDate, endDate);

        // Creamos el DTO que contendrá toda la información del reporte
        MonthlyReportDTO report = new MonthlyReportDTO();

        // Procesamos cada timelog y lo organizamos por proyectos y actividades
        for (Timelog timelog : timelogs) {
            String projectName = timelog.getProject().getProjectName();
            String activityName = timelog.getActivity().getActivityName();

            // Agregamos el tiempo a su respectivo proyecto
            ProjectReportDTO projectReport = report.getProjectReports().computeIfAbsent(
                projectName, name -> new ProjectReportDTO(name));

            // Agregamos el tiempo a su respectiva actividad dentro del proyecto
            ActivityReportDTO activityReport = projectReport.getActivities().computeIfAbsent(
                activityName, name -> new ActivityReportDTO(name));

            // Sumamos el tiempo de la actividad al reporte de la actividad
            activityReport.addTimeSpent(timelog.getDuration());

            // Actualizamos el tiempo total invertido en el proyecto
            projectReport.addTotalTime(timelog.getDuration());
        }

        return report;
    }
}
