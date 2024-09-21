package com.diavlo.bitacora.activityassignments.domain.entity;

import java.sql.Timestamp;

import com.diavlo.bitacora.activities.domain.entity.Activity;
import com.diavlo.bitacora.users.domain.entity.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.ForeignKey;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "activityassignments", uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "activity_id"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AcitvityAssignment {

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long assignmentId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_assignment_user"))
    private User user;

    @ManyToOne
    @JoinColumn(name = "activity_id", nullable = false, foreignKey = @ForeignKey(name = "fk_assignment_activity"))
    private Activity activity;

    @Column(nullable = false, length = 50)
    private String role;

    @Column(name = "assigned_at")
    private Timestamp assignedAt;
}
