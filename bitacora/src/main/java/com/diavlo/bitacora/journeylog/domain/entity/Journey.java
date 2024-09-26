package com.diavlo.bitacora.journeylog.domain.entity;

import java.time.LocalDateTime;

import com.diavlo.bitacora.common.domain.entities.TimeCreateUpdate;
import com.diavlo.bitacora.users.domain.entity.User;
import jakarta.persistence.ForeignKey;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "journey")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Journey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long journeyId;

    @ManyToOne // Establecer relación con la entidad User
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_journeylog_user"))
    private User userId; // Relación con el usuario

    @Column(name = "entry_time", nullable = false)
    private LocalDateTime entryTime; // Hora de entrada

    @Column(name = "exit_time")
    private LocalDateTime exitTime; // Hora de salida (puede ser null si no ha salido aún)


}
