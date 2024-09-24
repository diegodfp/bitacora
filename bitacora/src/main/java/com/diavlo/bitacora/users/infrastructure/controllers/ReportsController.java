package com.diavlo.bitacora.users.infrastructure.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.diavlo.bitacora.users.application.dto.MonthlyReportDTO;
import com.diavlo.bitacora.users.domain.services.ReportService;

import java.time.LocalDate;

@RestController
@RequestMapping("/user/{userId}/report")
public class ReportsController {
 @Autowired
    private ReportService reportService;

    @GetMapping
    public ResponseEntity<MonthlyReportDTO> generateMonthlyReport(@PathVariable Long userId,
                                                                 @RequestParam("startDate") String startDate,
                                                                 @RequestParam("endDate") String endDate) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        MonthlyReportDTO report = reportService.generateMonthlyReport(userId, start, end);
        return ResponseEntity.ok(report);
    }
}
