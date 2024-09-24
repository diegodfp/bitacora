package com.diavlo.bitacora.activitychangestatus.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diavlo.bitacora.activitychangestatus.domain.entity.ActivityStatusChange;

public interface ActivityChangeStatusRepository extends JpaRepository<ActivityStatusChange, Long>{

}
