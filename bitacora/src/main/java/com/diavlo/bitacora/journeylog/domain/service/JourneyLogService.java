package com.diavlo.bitacora.journeylog.domain.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diavlo.bitacora.journeylog.domain.entity.Journey;
import com.diavlo.bitacora.journeylog.infrastructure.repository.JourneyLogRepository;

@Service
public class JourneyLogService {
    @Autowired
    private JourneyLogRepository journeyLogRepository;

    public Journey saveJourneyLog(Journey journeyLog) {
        return journeyLogRepository.save(journeyLog);
    }
}
