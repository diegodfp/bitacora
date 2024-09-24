package com.diavlo.bitacora.users.infrastructure.controllers;

import java.util.*;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.diavlo.bitacora.activities.application.dto.ActivityDTO;
import com.diavlo.bitacora.activities.domain.services.ActivityService;
import com.diavlo.bitacora.timelogs.domain.service.TimelogService;

@RestController
@RequestMapping("/user")
public class UserController {

    private final ActivityService activityService;

    @Autowired
    private  TimelogService timelogService;

    public UserController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @GetMapping("/{userId}/activities")
    public ResponseEntity<List<ActivityDTO>> getUserActivities(@PathVariable Long userId) {
        try {
            List<ActivityDTO> activities = activityService.getUserActivities(userId);
            System.out.println("Activities for user " + userId + ": " + activities);
            return ResponseEntity.ok(activities);
        } catch (IllegalArgumentException e) {
            // Manejo de error cuando el usuario no se encuentra
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            // Manejo de otros errores generales
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Ver Registro de Tiempo Diario
    @GetMapping("/{userId}/timelogs")
    public ResponseEntity<?> getTimelogsByUserAndDate(
            @PathVariable Long userId,
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        int totalTime = timelogService.getTotalTimeByUserAndDate(userId, date);
        return ResponseEntity.ok("Total time for " + date + ": " + totalTime + " minutes");
    }

}
