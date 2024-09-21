package com.diavlo.bitacora.activitychangestatus.domain.entity;

import com.diavlo.bitacora.activities.domain.entity.Activity;
import com.diavlo.bitacora.activitystatuses.domain.entity.ActivityStatus;
import com.diavlo.bitacora.users.domain.entity.User;
import jakarta.persistence.Column;
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

import java.time.LocalDateTime;

@Entity
@Table(name = "activity_status_changes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ActivityStatusChange {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long activityStatusChangeId;

    @ManyToOne
    @JoinColumn(name = "activity_id", nullable = false, foreignKey = @ForeignKey(name = "fk_activity_status_change_activity"))
    private Activity activity;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false, foreignKey = @ForeignKey(name = "fk_activity_status_change_status"))
    private ActivityStatus activityStatus;

    @ManyToOne
    @JoinColumn(name = "changed_by_user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_activity_status_change_user"))
    private User changedByUser;

    @Column(name = "change_comment", nullable = false, columnDefinition = "text")
    private String changeComment;

    @Column(name = "changed_at", nullable = false)
    private LocalDateTime changedAt; 

}
