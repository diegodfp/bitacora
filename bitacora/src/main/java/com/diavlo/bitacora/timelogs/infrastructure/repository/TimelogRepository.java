package com.diavlo.bitacora.timelogs.infrastructure.repository;

import java.util.Optional;
import java.util.List;
import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.diavlo.bitacora.timelogs.domain.entity.Timelog;

@Repository
public interface TimelogRepository extends JpaRepository<Timelog, Long> {

    @Query("SELECT t FROM Timelog t WHERE t.activity.activityId = :activityId AND t.user.userId = :userId AND t.project.projectId = :projectId AND t.endTime IS NULL")
    Optional<Timelog> findOngoingActivity(Long activityId, Long userId, Long projectId);

    @Query("SELECT t FROM Timelog t WHERE t.user.id = :userId AND t.workDate = :date")
    List<Timelog> findByUserAndDate(@Param("userId") Long userId, @Param("date") LocalDate date);

     // Consulta para obtener registros por actividad
     @Query("SELECT t FROM Timelog t WHERE t.activity.id = :activityId")
     List<Timelog> findByActivity(@Param("activityId") Long activityId);

    @Query("SELECT t FROM Timelog t WHERE t.user.userId = :userId AND t.workDate BETWEEN :startDate AND :endDate")
    List<Timelog> findByUserIdAndWorkDateBetween(@Param("userId") Long userId, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);;
}
