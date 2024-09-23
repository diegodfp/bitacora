package com.diavlo.bitacora.timelogs.domain.service;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.diavlo.bitacora.timelogs.domain.entity.Timelog;
import com.diavlo.bitacora.timelogs.infrastructure.repository.TimelogRepository;
import com.diavlo.bitacora.activities.domain.entity.Activity;
import com.diavlo.bitacora.activities.infrastructure.repository.ActivityRepository;
import com.diavlo.bitacora.projects.domain.entity.Project;
import com.diavlo.bitacora.projects.infrastructure.repository.ProjectRepository;
import com.diavlo.bitacora.users.domain.entity.User;
import com.diavlo.bitacora.users.infrastructure.repositories.UserRepository;


@Service
public class TimelogService {

    @Autowired
    private TimelogRepository timelogRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Transactional
    public void startActivity(Long activityId, Long userId, Long projectId) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Activity> activity = activityRepository.findById(activityId);
        Optional<Project> project = projectRepository.findById(projectId);

        if (user.isPresent() && activity.isPresent() && project.isPresent()) {
            Timelog timelog = Timelog.builder()
                .user(user.get())
                .activity(activity.get())
                .project(project.get())
                .workDate(new java.sql.Date(System.currentTimeMillis()))
                .startTime(new Timestamp(System.currentTimeMillis()))
                .build();

            timelogRepository.save(timelog);
        } else {
            throw new IllegalArgumentException("Datos inválidos para iniciar la actividad.");
        }
    }

    @Transactional
    public void pauseActivity(Long activityId, Long userId, Long projectId) {
        Optional<Timelog> timelogOptional = timelogRepository.findOngoingActivity(activityId, userId, projectId);

        if (timelogOptional.isPresent()) {
            Timelog timelog = timelogOptional.get();
            Timestamp endTime = new Timestamp(System.currentTimeMillis());

            // Calcular la duración en segundos
            long durationInSeconds = Duration.between(timelog.getStartTime().toLocalDateTime(), endTime.toLocalDateTime()).getSeconds();
            
            // Actualizar el timelog
            timelog.setEndTime(endTime);
            timelog.setDuration((int) durationInSeconds);  // Guardar duración en segundos
            timelogRepository.save(timelog);
        } else {
            throw new IllegalArgumentException("No hay actividad en curso para pausar.");
        }
    }
}
