package com.diavlo.bitacora.activities.infrastructure.controller;

import com.diavlo.bitacora.activities.application.dto.ActivityDTO;
import com.diavlo.bitacora.activities.domain.services.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/activities")
@RequiredArgsConstructor
public class ActivityController {

    private final ActivityService activityService;

    // Crear una actividad
    @PostMapping("/create")
    public ResponseEntity<ActivityDTO> createActivity(@RequestBody ActivityDTO activityDTO) {
        return ResponseEntity.ok(activityService.createActivity(activityDTO));
    }

    // Actualizar una actividad
    @PutMapping("/update/{id}")
    public ResponseEntity<ActivityDTO> updateActivity(@PathVariable Long id, @RequestBody ActivityDTO activityDTO) {
        Optional<ActivityDTO> updatedActivity = activityService.updateActivity(id, activityDTO);
        return updatedActivity.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Eliminar una actividad
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteActivity(@PathVariable Long id) {
        if (activityService.deleteActivity(id)) {
            return ResponseEntity.ok("Activity deleted successfully");
        }
        return ResponseEntity.notFound().build();
    }

    // Obtener una actividad por ID
    @GetMapping("/find/{id}")
    public ResponseEntity<ActivityDTO> getActivityById(@PathVariable Long id) {
        Optional<ActivityDTO> activityDTO = activityService.getActivityById(id);
        return activityDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Listar todas las actividades
    @GetMapping("/list")
    public ResponseEntity<List<ActivityDTO>> getAllActivities() {
        List<ActivityDTO> activities = activityService.getAllActivities();
        return ResponseEntity.ok(activities);
    }

     // JSON para Insomnia:
    /*
        POST / PUT JSON:
        {
            "projectId": 1,
            "activityTypeId": 2,
            "activityStatusId": 3,
            "priorityId": 4,
            "createdByUserId": 5,
            "activityName": "Activity 1",
            "description": "Actividad",
            "startDate": "2024-09-22T10:00:00",
            "updatedDate": "2024-09-22T12:00:00"
        }
    */
}
