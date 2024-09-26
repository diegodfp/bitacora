package com.diavlo.bitacora.journeylog.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diavlo.bitacora.journeylog.domain.entity.Journey;

public interface JourneyLogRepository extends JpaRepository<Journey, Long> {

}
