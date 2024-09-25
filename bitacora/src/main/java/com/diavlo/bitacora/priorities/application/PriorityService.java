package com.diavlo.bitacora.priorities.application;

import java.util.List;
import java.util.Optional;

import com.diavlo.bitacora.priorities.domain.entity.Priority;

public interface PriorityService {
    List<Priority> findAll();
    Optional<Priority> findById(Long id);
}
