package com.diavlo.bitacora.priorities.domain.service;


import org.springframework.stereotype.Service;

import com.diavlo.bitacora.priorities.application.dto.PriorityDTO;
import com.diavlo.bitacora.priorities.infrastructure.repository.PriorityRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PriorityService {
 private final PriorityRepository priorityRepository;

    public PriorityService(PriorityRepository priorityRepository) {
        this.priorityRepository = priorityRepository;
    }
     public List<PriorityDTO> getAllPriorities() {
        return priorityRepository.findAll().stream()
            .map(priority -> new PriorityDTO(priority.getPriorityId(), priority.getPriorityLevel(), priority.getDescription()))
            .collect(Collectors.toList());
    }
}
