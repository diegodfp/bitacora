package com.diavlo.bitacora.activitytypes.domain.service;

import org.springframework.stereotype.Service;

import com.diavlo.bitacora.activitytypes.application.dto.ActivityTypeDTO;
import com.diavlo.bitacora.activitytypes.infrastructure.repository.ActivityTypeRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActivityTypeService {
    private final ActivityTypeRepository activityTypeRepository;
public ActivityTypeService(ActivityTypeRepository activityTypeRepository) {
        this.activityTypeRepository = activityTypeRepository;
    }

    public List<ActivityTypeDTO> getAllActivityTypes() {
        return activityTypeRepository.findAll().stream()
            .map(type -> new ActivityTypeDTO(type.getActivityTypeId(), type.getTypeName(), type.getDescription()))
            .collect(Collectors.toList());
    }
}
