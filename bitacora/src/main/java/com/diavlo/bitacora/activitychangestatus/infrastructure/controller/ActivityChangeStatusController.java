package com.diavlo.bitacora.activitychangestatus.infrastructure.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diavlo.bitacora.activitychangestatus.application.ActivityChangeStatuService;
import com.diavlo.bitacora.activitychangestatus.domain.entity.ActivityChangeStatusDTO;
import com.diavlo.bitacora.activitychangestatus.domain.entity.ActivityStatusChange;



@RequestMapping("/ActivityChangeStatusController")
@RestController
public class ActivityChangeStatusController {
    @Autowired
    private ActivityChangeStatuService  activityChangeStatuService;

    @GetMapping("/findAll")
    public  List<ActivityStatusChange> findAll(){
        return activityChangeStatuService.findAll();
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Optional<ActivityStatusChange> history_Register = activityChangeStatuService.findById(id);
        if (history_Register.isPresent()) {
            return ResponseEntity.ok(activityChangeStatuService.findById(id));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("History Register with id " + id + " not found.");
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ActivityChangeStatusDTO activityChangeStatusDTO){
        try {
            Optional<ActivityStatusChange>  activityStatusChangeOpt = activityChangeStatuService.create(activityChangeStatusDTO);
            if (activityStatusChangeOpt.isPresent()) {
                // Si el cambio de estado de actividad fue creado exitosamente, devolvemos 201
                return ResponseEntity.status(HttpStatus.CREATED).body(activityStatusChangeOpt.get());
            } else {
                // Si no se pudo crear, devolver un 400 Bad Request
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The activity status change could not be created.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error when creating the activity status change.");
        }
    }


}
