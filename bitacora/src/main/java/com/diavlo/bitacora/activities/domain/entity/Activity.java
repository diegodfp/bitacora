package com.diavlo.bitacora.activities.domain.entity;

import com.diavlo.bitacora.activitystatuses.domain.entity.ActivityStatus;
import com.diavlo.bitacora.activitytypes.domain.entity.ActivityType;
import com.diavlo.bitacora.common.domain.entities.TimeCreateUpdate;
import com.diavlo.bitacora.priorities.domain.entity.Priority;
import com.diavlo.bitacora.projects.domain.entity.Project;
import com.diavlo.bitacora.users.domain.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "activities", uniqueConstraints = @UniqueConstraint(columnNames = {"project_id", "activity_name"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long activityId;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "project_id", nullable = false, foreignKey = @ForeignKey(name = "fk_activity_project"))
    private Project project;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "activity_type_id", nullable = false, foreignKey = @ForeignKey(name = "fk_activity_type"))
    private ActivityType activityType;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "activity_status_id", nullable = false, foreignKey = @ForeignKey(name = "fk_activity_status"))
    private ActivityStatus activityStatus;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "priority_id", nullable = false, foreignKey = @ForeignKey(name = "fk_activity_priority"))
    private Priority priority;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "created_by_user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_activity_user"))
    private User createdByUser;

    @Column(name = "activity_name", nullable = false, length = 100)
    private String activityName;

    @Column(columnDefinition = "text")
    private String description;

    @Embedded
    private TimeCreateUpdate timeCreateUpdate;
}
