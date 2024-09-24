package com.diavlo.bitacora.activityassignments.infrastructure.controller;

import com.diavlo.bitacora.activityassignments.application.ActivityAssignmentDTO;
import com.diavlo.bitacora.activityassignments.domain.entity.ActivityAssignment;
import com.diavlo.bitacora.activityassignments.domain.service.ActivityAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/assignments")
public class ActivityAssignmentController {

    @Autowired
    private ActivityAssignmentService assignmentService;

    @PostMapping("/activity")
    public ResponseEntity<ActivityAssignment> assignActivity(@RequestBody ActivityAssignmentDTO  request) {
        ActivityAssignment assignment = assignmentService.assignActivity(
                request.getUserId(), 
                request.getActivityId(), 
                request.getRole());
        return ResponseEntity.ok(assignment);
    }
}
