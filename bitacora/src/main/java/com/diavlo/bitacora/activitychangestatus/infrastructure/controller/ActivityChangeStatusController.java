package com.diavlo.bitacora.activitychangestatus.infrastructure.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diavlo.bitacora.activitychangestatus.application.ActivityChangeStatuService;

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

}
