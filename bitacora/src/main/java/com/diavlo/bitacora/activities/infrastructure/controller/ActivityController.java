package com.diavlo.bitacora.activities.infrastructure.controller;

import com.diavlo.bitacora.activities.application.dto.ActivityChangeStatusDTO;
import com.diavlo.bitacora.activities.application.dto.ActivityDTO;
import com.diavlo.bitacora.activities.application.dto.contenedorDTO;
import com.diavlo.bitacora.activities.domain.entity.StartPauseRequest;
import com.diavlo.bitacora.activities.domain.services.ActivityService;
import com.diavlo.bitacora.timelogs.domain.service.TimelogService;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/activities")
@RequiredArgsConstructor
public class ActivityController {

    private final ActivityService activityService;
    private final TimelogService timelogService;
    // Crear una actividad
    @PostMapping("/create")
    public ResponseEntity<ActivityDTO> createActivity(@RequestBody ActivityDTO activityDTO) {
        return ResponseEntity.ok(activityService.createActivity(activityDTO));
    }

    // Actualizar una actividad
    @PutMapping("/update/{id}")
    public ResponseEntity<ActivityDTO> updateActivity(@PathVariable Long id, @RequestBody contenedorDTO request) {
        Optional<ActivityDTO> updatedActivity = activityService.updateActivity(id, request.getActivityDTO(), request.getActivityChangeStatusDTO());
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
    

    @PostMapping("/{activityId}/start")
public ResponseEntity<?> startActivity(@PathVariable Long activityId, 
                                       @RequestBody StartPauseRequest request) {
    timelogService.startActivity(activityId, request.getUserId(), request.getProjectId());
    return ResponseEntity.ok("Actividad Iniciada");
}

/*  ENDPOINT INSOMNIA
    http://localhost:8080/activities/1/start
 {
    "userId": 3,
    "projectId": 1
}
 */

@PostMapping("/{activityId}/pause")
public ResponseEntity<?> pauseActivity(@PathVariable Long activityId, 
                                       @RequestBody StartPauseRequest request) {
    timelogService.pauseActivity(activityId, request.getUserId(), request.getProjectId());
    return ResponseEntity.ok("Actividad Pausada");
}
/*
 http://localhost:8080/activities/1/pause

 {
    "userId": 3,
    "projectId": 1
}

 */


    

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


    // Ver Tiempo Total Invertido en una Actividad
    @GetMapping("/{activityId}/timelog")
    public ResponseEntity<?> getTotalTimeForActivity(@PathVariable Long activityId) {
        int totalTime = timelogService.getTotalTimeByActivity(activityId);
        return ResponseEntity.ok("Total time for activity: " + totalTime + " minutes");
    }


}


// {
//     "activityDTO": {
//         "activityId": 1,
//         "projectId": 1,
//         "activityTypeId": 1,
//         "activityStatusId": 2,
//         "priorityId": 1,
//         "createdByUserId":1,
//         "activityName": "Actividad de Ejemplo",
//         "description": "Descripción de la actividad",
//         "startDate": "2024-09-22T10:00:00",
//         "updatedDate": "2024-09-22T12:00:00"
//     },
//     "activityChangeStatusDTO": {
//         "activity_id": 1,
//         "changed_by_user_id": 2,
//         "status_id": 2,
//         "change_comment": "Cambio de estado"
//     }
// }

