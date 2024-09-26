package com.diavlo.bitacora.journeylog.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.diavlo.bitacora.journeylog.domain.entity.Journey;
import com.diavlo.bitacora.journeylog.domain.service.JourneyLogService;

@RestController
@RequestMapping("/api/journeylogs")
public class JourneyController {

    @Autowired
    private JourneyLogService journeyLogService; // Inyección de dependencias

    @PostMapping
    public ResponseEntity<Journey> createJourneyLog(@RequestBody Journey journeyLog) {
        Journey savedJourneyLog = journeyLogService.saveJourneyLog(journeyLog);
        return ResponseEntity.ok(savedJourneyLog);
    }
}
