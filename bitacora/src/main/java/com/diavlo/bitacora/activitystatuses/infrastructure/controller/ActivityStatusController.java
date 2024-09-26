package com.diavlo.bitacora.activitystatuses.infrastructure.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diavlo.bitacora.activitystatuses.application.dto.ActivityStatusDTO;
import com.diavlo.bitacora.activitystatuses.domain.service.ActivityStatusService;

import java.util.List;

    @RestController
    @RequestMapping("/activity-statuses")
    public class ActivityStatusController {

        private final ActivityStatusService activityStatusService;

        public ActivityStatusController(ActivityStatusService activityStatusService) {
            this.activityStatusService = activityStatusService;
        }

        @GetMapping
        public ResponseEntity<List<ActivityStatusDTO>> getAllActivityStatuses() {
            List<ActivityStatusDTO> statuses = activityStatusService.getAllActivityStatuses();
            return ResponseEntity.ok(statuses);
        }

    }
