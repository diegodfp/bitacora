package com.diavlo.bitacora.projectusers.domain.entity;

import com.diavlo.bitacora.projects.domain.entity.Project;
import com.diavlo.bitacora.users.domain.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "project_users", uniqueConstraints = @UniqueConstraint(columnNames = {"project_id", "user_id"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_user_id")
    private Long projectUserId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false, foreignKey = @ForeignKey(name = "fk_project_user_project"))
    @JsonIgnore // Evitar la serialización del proyecto para romper el ciclo
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_project_user_user"))
    @JsonIgnore // Evitar la serialización del usuario para romper el ciclo
    private User user;

    @Column(name = "is_leader", nullable = false)
    private int isLeader;

    @Column(name = "assigned_at", length = 50)
    private LocalDateTime assignedDate; 
}
