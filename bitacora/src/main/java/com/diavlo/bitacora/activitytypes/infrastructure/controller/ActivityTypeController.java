package com.diavlo.bitacora.activitytypes.infrastructure.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diavlo.bitacora.activitytypes.application.dto.ActivityTypeDTO;
import com.diavlo.bitacora.activitytypes.domain.service.ActivityTypeService;

import java.util.List;

@RestController
@RequestMapping("/activity-types")
public class ActivityTypeController {
 private final ActivityTypeService activityTypeService;

    public ActivityTypeController(ActivityTypeService activityTypeService) {
        this.activityTypeService = activityTypeService;
    }

    @GetMapping
    public ResponseEntity<List<ActivityTypeDTO>> getAllActivityTypes() {
        List<ActivityTypeDTO> activityTypes = activityTypeService.getAllActivityTypes();
        return ResponseEntity.ok(activityTypes);
    }
}
