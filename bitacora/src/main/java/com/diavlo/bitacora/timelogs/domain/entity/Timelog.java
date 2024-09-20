package com.diavlo.bitacora.timelogs.domain.entity;



import java.sql.Date;
import java.sql.Timestamp;

import com.diavlo.bitacora.activities.domain.entity.Activity;
import com.diavlo.bitacora.common.domain.entities.TimeCreateUpdate;
import com.diavlo.bitacora.projects.domain.entity.Project;
import com.diavlo.bitacora.users.application.entity.User;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.ForeignKey;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "timelogs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Timelog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long timelogId;

    // Relación con la tabla Users
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_timelog_user"))
    private User user;

    // Relación con la tabla Activities
    @ManyToOne
    @JoinColumn(name = "activity_id", nullable = false, foreignKey = @ForeignKey(name = "fk_timelog_activity"))
    private Activity activity;

    // Relación con la tabla Projects
    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false, foreignKey = @ForeignKey(name = "fk_timelog_project"))
    private Project project;

    // Fecha del trabajo
    @Column(name = "work_date", nullable = false)
    private Date workDate;

    // Hora de inicio del trabajo
    @Column(name = "start_time", nullable = false)
    private Timestamp startTime;

    // Hora de fin del trabajo (puede ser nulo)
    @Column(name = "end_time")
    private Timestamp endTime;

    // Duración del trabajo en minutos (opcional)
    @Column(columnDefinition = "int")
    private Integer duration;

    // Descripción opcional
    @Column(columnDefinition = "text")
    private String description;

    // Tiempos de creación y actualización embebidos
    @Embedded
    private TimeCreateUpdate timeCreateUpdate;

}
