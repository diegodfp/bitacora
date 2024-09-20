package com.diavlo.bitacora.deparments.domain.entity;



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
@Table(name = "departments", uniqueConstraints = @UniqueConstraint(columnNames = "department_name"))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long departmentId;

    @Column(name = "department_name", columnDefinition = "varchar(100)", nullable = false)
    private String departmentName;

    @Column(columnDefinition = "text")
    private String description;

    @Embedded
    private TimeCreateUpdate timeCreateUpdate;

}
