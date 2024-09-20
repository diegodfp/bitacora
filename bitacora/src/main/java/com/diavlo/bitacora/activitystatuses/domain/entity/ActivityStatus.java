package com.diavlo.bitacora.activitystatuses.domain.entity;


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
@Table(name = "activitystatuses", uniqueConstraints = @UniqueConstraint(columnNames = "status_name"))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActivityStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long statusId;

    @Column(name = "status_name", columnDefinition = "varchar(50)", nullable = false)
    private String statusName;

    @Column(columnDefinition = "text")
    private String description;

    @Embedded
    private TimeCreateUpdate timeCreateUpdate;

}
