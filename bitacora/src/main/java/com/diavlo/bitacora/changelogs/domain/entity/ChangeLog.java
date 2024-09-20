package com.diavlo.bitacora.changelogs.domain.entity;


import java.time.LocalDateTime;

import com.diavlo.bitacora.users.application.entity.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "changelogs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChangeLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "changelog_id")
    private Integer changelogId;

    // Relación ManyToOne con la tabla Users, eliminamos el campo userId independiente
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "FK_Users_ChangeLogs"))
    private User user;

    // Enumerado para el tipo de entidad (PROJECT, ACTIVITY, USER, ASSIGNMENT)
    @Enumerated(EnumType.STRING)
    @Column(name = "entity_type", nullable = false)
    private EntityType entityType;

    @Column(name = "entity_id", nullable = false)
    private Integer entityId;

    // Enumerado para la acción (CREATE, UPDATE, DELETE, TERMINATE)
    @Enumerated(EnumType.STRING)
    @Column(name = "action", nullable = false)
    private Action action;

    @Column(name = "change_description", columnDefinition = "TEXT")
    private String changeDescription;

    // Definición del timestamp para el cambio
    @Column(name = "change_timestamp", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime changeTimestamp;

    // Enumerado para definir el tipo de entidad
    public enum EntityType {
        PROJECT, ACTIVITY, USER, ASSIGNMENT
    }

    // Enumerado para definir la acción realizada
    public enum Action {
        CREATE, UPDATE, DELETE, TERMINATE
    }
}
