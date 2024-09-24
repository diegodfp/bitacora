package com.diavlo.bitacora.activitychangestatus.application;

import java.util.List;
import java.util.Optional;


import com.diavlo.bitacora.activitychangestatus.domain.entity.ActivityStatusChange;

public interface ActivityChangeStatuService {
    List<ActivityStatusChange> findAll();
    Optional<ActivityStatusChange> findById(Long id);
}
