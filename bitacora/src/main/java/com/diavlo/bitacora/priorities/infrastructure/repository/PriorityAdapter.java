package com.diavlo.bitacora.priorities.infrastructure.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diavlo.bitacora.priorities.application.PriorityService;
import com.diavlo.bitacora.priorities.domain.entity.Priority;

@Service
public class PriorityAdapter implements PriorityService{
    @Autowired
    private PriorityRepository priorityRepository;

    @Override
    public List<Priority> findAll() {
        return priorityRepository.findAll();
    }

    @Override
    public Optional<Priority> findById(Long id) {
        return priorityRepository.findById(id);
    }

}
