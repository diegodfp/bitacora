package com.diavlo.bitacora.priorities.domain.entity;


import com.diavlo.bitacora.common.domain.entities.TimeCreateUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "priorities", uniqueConstraints = @UniqueConstraint(columnNames = "priority_level"))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Priority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long priorityId;

    @Column(name = "priority_level", columnDefinition = "varchar(50)", nullable = false)
    private String priorityLevel;

    @Column(columnDefinition = "text")
    private String description;

    @Embedded
    private TimeCreateUpdate timeCreateUpdate;

}
