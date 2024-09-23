package com.diavlo.bitacora.activities.infrastructure.repository;

import com.diavlo.bitacora.activities.domain.entity.Activity;
import com.diavlo.bitacora.users.domain.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
   
}
