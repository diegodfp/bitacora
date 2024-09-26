package com.diavlo.bitacora.activitystatuses.domain.service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.diavlo.bitacora.activitystatuses.application.dto.ActivityStatusDTO;
import com.diavlo.bitacora.activitystatuses.infrastructure.repository.ActivityStatusRepository;

@Service
public class ActivityStatusService {

    private final ActivityStatusRepository activityStatusRepository;

    public ActivityStatusService(ActivityStatusRepository activityStatusRepository) {
        this.activityStatusRepository = activityStatusRepository;
    }

     public List<ActivityStatusDTO> getAllActivityStatuses() {
        return activityStatusRepository.findAll().stream()
            .map(status -> new ActivityStatusDTO(status.getStatusId(), status.getStatusName()))
            .collect(Collectors.toList());
    }

}
